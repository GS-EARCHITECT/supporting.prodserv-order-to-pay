package order_items_mgmt.prod_asset.allocation.services.scheduler;

import java.sql.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import common.model.master.ServiceWorkMaster;
import jobs.job_mgmt.model.repo.scheduler.JobTripRepo;
import order_items_mgmt.prod_asset.allocation.model.repo.common.*;
import order_items_mgmt.prod_asset.allocation.model.repo.scheduler.*;
import common.model.master.*;

@Service("resourceAllocMgmtServ")
public class ResourceAllocMgmt_Service implements I_ResourceAllocMgmt_Service {

	private static final Logger logger = LoggerFactory.getLogger(ResourceAllocMgmt_Service.class);

	@Autowired
	private JobWorkDetailsRead_Repo jobWorkDetailsReadRepo;

	@Autowired
	private JobTripRepo jobTripRepo;

	@Autowired
	private StoreOrderAssetInwardsCUDPublic_Repo storeOrderAssetInwardsCUDPublicRepo;

	@Autowired
	private StoreOrderResourceInwardsCUDPublic_Repo storeOrderResourceInwardsCUDPublicRepo;

	@Autowired
	private StoreOrderResourceOutwardsCUDPublic_Repo storeOrderResourceOutwardsCUDPublicRepo;

	@Autowired
	private StoreOrderAssetOutwardsCUDPublic_Repo storeOrderAssetOutwardsCUDPublicRepo;

	@Autowired
	private JobAssetMaster_Repo jobAssetMaster_Repo;

	@Autowired
	private JobResourceMaster_Repo jobResourceMaster_Repo;

	@Autowired
	private ServiceWorkMasterCUD_Repo serviceWorkMasterCUDRepo;

	@Autowired
	private ServiceWorkMasterRead_Repo serviceWorkMasterReadRepo;

	/* Get Resource Template For Service Type */
	// @Scheduled(fixedRate = 3000)
	public void resource_ScheduledAlloc() {
		logger.info("starting resource scheduler");
		CopyOnWriteArrayList<ServiceWorkMaster> workServs = null;
		workServs = serviceWorkMasterReadRepo.getSelectWorksForAutoAllocResourcesNotAllocated();
		CopyOnWriteArrayList<JobWorkDetail> jobsList = null;

		// For each service work in service master
		for (int i = 0; i < workServs.size(); i++) {
			jobsList = jobWorkDetailsReadRepo.getSelectJobWorkDetailForService(workServs.get(i).getServiceWorkSeqNo());
			doAlloc(jobsList);
			serviceWorkMasterCUDRepo.updResourceAllocStatus(workServs.get(i).getServiceWorkSeqNo(), 'Y');
		}
	}

	public synchronized void doAlloc(CopyOnWriteArrayList<JobWorkDetail> jobsList) {
		CopyOnWriteArrayList<JobTripDetails> jobTrips = null;
		Long jobWorkSeqNo = (long) 0;
		Long jobSeqNo = null;
		Long targetSeqNo = (long) 0;
		Timestamp pl_start, pl_end, ac_start, ac_end;

		// For each job for service work in job details
		for (int j = 0; j < jobsList.size(); j++) {
			pl_start = null;
			pl_end = null;
			ac_start = null;
			ac_end = null;
			targetSeqNo = (long) 0;
			jobSeqNo = jobsList.get(j).getJobSeqNo();
			jobWorkSeqNo = jobsList.get(j).getJobWorkSeqNo();
			targetSeqNo = jobsList.get(j).getTargetSeqNo();
			pl_start = jobsList.get(j).getPlanStartDate();
			pl_end = jobsList.get(j).getPlanEndDate();
			ac_start = jobsList.get(j).getActStartDate();
			ac_end = jobsList.get(j).getActEndDate();

			if (pl_start != null || pl_end != null || ac_start != null || ac_end != null) 
			{
				if (ac_start != null && ac_end != null) {
					jobTrips = jobTripRepo.getJobActualDetails(jobWorkSeqNo);
				} else {
					jobTrips = jobTripRepo.getJobPlanDetails(jobWorkSeqNo);
				}

				if (jobTrips != null && jobTrips.size() > 0) 
				{
					for (int j7 = 0; j7 < jobTrips.size(); j7++) {

						if (ac_start != null && ac_end != null) 
						{
							allocate_Assets(jobWorkSeqNo, jobSeqNo, jobTrips.get(j7).getJob_Trip_pk().getModeSeqNo(),
									targetSeqNo, jobTrips.get(j7).getJob_Trip_pk().getFromLocationSeqNo(),
									jobTrips.get(j7).getJob_Trip_pk().getToLocationSeqNo(), pl_start, pl_end);
						} else {
							allocate_Assets(jobWorkSeqNo, jobSeqNo, jobTrips.get(j7).getJob_Trip_pk().getModeSeqNo(),
									targetSeqNo, jobTrips.get(j7).getJob_Trip_pk().getFromLocationSeqNo(),
									jobTrips.get(j7).getJob_Trip_pk().getToLocationSeqNo(), ac_start, ac_end);
						}

						if (ac_start != null && ac_end != null) {
							allocate_Resources(jobWorkSeqNo, jobSeqNo, jobTrips.get(j7).getJob_Trip_pk().getModeSeqNo(),
									targetSeqNo, jobTrips.get(j7).getJob_Trip_pk().getFromLocationSeqNo(),
									jobTrips.get(j7).getJob_Trip_pk().getToLocationSeqNo(), pl_start, pl_end);
						} else {
							allocate_Resources(jobWorkSeqNo, jobSeqNo, jobTrips.get(j7).getJob_Trip_pk().getModeSeqNo(),
									targetSeqNo, jobTrips.get(j7).getJob_Trip_pk().getFromLocationSeqNo(),
									jobTrips.get(j7).getJob_Trip_pk().getToLocationSeqNo(), ac_start, ac_end);

						}
					}
				}
				
				if (jobTrips == null || jobTrips.size() <= 0) 
				{
					if (ac_start != null && ac_end != null) 
					{
						allocate_Assets(jobWorkSeqNo, jobSeqNo, 0, targetSeqNo, (long)0, (long)0, pl_start, pl_end);
					} 
					else 
					{
						allocate_Assets(jobWorkSeqNo, jobSeqNo, 0, targetSeqNo, (long)0, (long)0, ac_start, ac_end);
					}

					if (ac_start != null && ac_end != null) 
					{
						allocate_Resources(jobWorkSeqNo, jobSeqNo, 0, targetSeqNo, (long)0, (long)0, pl_start, pl_end);
					} 
					else 
					{
					allocate_Resources(jobWorkSeqNo, jobSeqNo, 0, targetSeqNo, (long)0, (long)0,ac_start, ac_end);
					}

				}
			}
		}
	}

	public synchronized void allocate_Assets(Long jobWorkSeqNo, Long jobSeqNo, Integer modeSeqNo, Long targetSeqNo,
			Long frLocSeqNo, Long toLocSeqNo, Timestamp frDtTm, Timestamp toDtTm) {
		storeOrderAssetInwardsCUDPublicRepo.markDeleteByJob(jobWorkSeqNo);
		storeOrderAssetOutwardsCUDPublicRepo.markDeleteByJob(jobWorkSeqNo);
		CopyOnWriteArrayList<JobAssetMaster> jobAssetMasters = null;
		StoreOrderAssetInward storeOrderAssetInward = null;
		StoreOrderAssetOutward storeOrderAssetOutward = null;
		jobAssetMasters = jobAssetMaster_Repo.getAssetsForJobType(jobSeqNo, targetSeqNo, modeSeqNo, frLocSeqNo,
				toLocSeqNo);

		if (jobAssetMasters != null && jobAssetMasters.size() > 0) {
			for (int i = 0; i < jobAssetMasters.size(); i++) {
				if (jobAssetMasters.get(i).getDirectionflag().equals('I')) {
					storeOrderAssetInward = new StoreOrderAssetInward();
					storeOrderAssetInward.setFlagAllocated(' ');
					storeOrderAssetInward.setAssetSeqNo(jobAssetMasters.get(i).getId().getAssetSeqNo());
					storeOrderAssetInward.setFrLocationSeqNo(frLocSeqNo);
					storeOrderAssetInward.setToLocationSeqNo(toLocSeqNo);				
					storeOrderAssetInward.setFromDttm(frDtTm);
					storeOrderAssetInward.setToDttm(toDtTm);
					storeOrderAssetInward.setJobWorkSeqNo(jobWorkSeqNo);
					storeOrderAssetInward.setTargetSeqNo(targetSeqNo);
					storeOrderAssetInward.setDoneflag('N');
					storeOrderAssetInward.setFlagBooked('N');
					storeOrderAssetInward.setIsBooked('N');
					storeOrderAssetInward.setModeTxn(modeSeqNo);
					storeOrderAssetInward.setMovedFlag('N');
					storeOrderAssetInward.setFlagRequested('Y');
					storeOrderAssetInward.setOkflag('N');
					storeOrderAssetInwardsCUDPublicRepo.save(storeOrderAssetInward);
				}

				if (jobAssetMasters.get(i).getDirectionflag().equals('O')) {
					storeOrderAssetOutward = new StoreOrderAssetOutward();
					storeOrderAssetOutward.setFlagAllocated(' ');
					storeOrderAssetOutward.setAssetSeqNo(jobAssetMasters.get(i).getId().getAssetSeqNo());
					storeOrderAssetOutward.setFrLocationSeqNo(frLocSeqNo);
					storeOrderAssetOutward.setToLocationSeqNo(toLocSeqNo);
					storeOrderAssetOutward.setFromDttm(frDtTm);
					storeOrderAssetOutward.setToDttm(toDtTm);
					storeOrderAssetOutward.setJobWorkSeqNo(jobWorkSeqNo);
					storeOrderAssetOutward.setTargetSeqNo(targetSeqNo);
					storeOrderAssetOutward.setDoneflag('N');
					storeOrderAssetOutward.setFlagBooked('N');
					storeOrderAssetOutward.setIsBooked('N');
					storeOrderAssetOutward.setModeTxn(modeSeqNo);
					storeOrderAssetOutward.setMovedFlag('N');
					storeOrderAssetOutward.setFlagRequested('Y');
					storeOrderAssetOutward.setOkflag('N');
					storeOrderAssetOutwardsCUDPublicRepo.save(storeOrderAssetOutward);
				}
			}
		}
	}

	public synchronized void allocate_Resources(Long jobWorkSeqNo, Long jobSeqNo, Integer modeSeqNo, Long targetSeqNo,
			Long frLocSeqNo, Long toLocSeqNo, Timestamp frDtTm, Timestamp toDtTm) {
		storeOrderResourceInwardsCUDPublicRepo.markDeleteByJob(jobWorkSeqNo);
		storeOrderResourceOutwardsCUDPublicRepo.markDeleteByJob(jobWorkSeqNo);
		CopyOnWriteArrayList<JobResourceMaster> jobResourceMasters = null;
		StoreOrderResourceInward storeOrderResourceInward = null;
		StoreOrderResourceOutward storeOrderResourceOutward = null;
		jobResourceMasters = jobResourceMaster_Repo.getResourcesForJobType(jobSeqNo, targetSeqNo, modeSeqNo, frLocSeqNo,
				toLocSeqNo);

		if (jobResourceMasters != null && jobResourceMasters.size() > 0) {
			for (int i = 0; i < jobResourceMasters.size(); i++) {
				if (jobResourceMasters.get(i).getDirectionflag().equals('I')) {
					storeOrderResourceInward = new StoreOrderResourceInward();
					storeOrderResourceInward.setQtyAllocated((float) 0);
					storeOrderResourceInward.setResourceSeqNo(jobResourceMasters.get(i).getId().getResourceSeqNo());
					storeOrderResourceInward.setFrLocationSeqN(frLocSeqNo);
					storeOrderResourceInward.setToLocationSeqNo(toLocSeqNo);
					storeOrderResourceInward.setFromDttm(frDtTm);
					storeOrderResourceInward.setToDttm(toDtTm);
					storeOrderResourceInward.setJobWorkSeqNo(jobWorkSeqNo);
					storeOrderResourceInward.setTargetSeqNo(targetSeqNo);
					storeOrderResourceInward.setDoneflag('N');
					storeOrderResourceInward.setQtyBooked((float) 0);
					storeOrderResourceInward.setIsBooked('N');
					storeOrderResourceInward.setModeTxn(modeSeqNo);
					storeOrderResourceInward.setMovedQty((float) 0);
					storeOrderResourceInward.setQtyRequested(jobResourceMasters.get(i).getQty());
					storeOrderResourceInward.setOkflag('N');
					storeOrderResourceInwardsCUDPublicRepo.save(storeOrderResourceInward);
				}

				if (jobResourceMasters.get(i).getDirectionflag().equals('O')) {
					storeOrderResourceOutward = new StoreOrderResourceOutward();
					storeOrderResourceOutward.setQtyAllocated((float) 0);
					storeOrderResourceOutward.setResourceSeqNo(jobResourceMasters.get(i).getId().getResourceSeqNo());
					storeOrderResourceOutward.setFrLocationSeqN(frLocSeqNo);
					storeOrderResourceOutward.setToLocationSeqNo(toLocSeqNo);
					storeOrderResourceOutward.setFromDttm(frDtTm);
					storeOrderResourceOutward.setToDttm(toDtTm);
					storeOrderResourceOutward.setJobWorkSeqNo(jobWorkSeqNo);
					storeOrderResourceOutward.setTargetSeqNo(targetSeqNo);
					storeOrderResourceOutward.setDoneflag('N');
					storeOrderResourceOutward.setQtyBooked((float) 0);
					storeOrderResourceOutward.setIsBooked('N');
					storeOrderResourceOutward.setModeTxn(modeSeqNo);
					storeOrderResourceOutward.setMovedQty((float) 0);
					storeOrderResourceOutward.setQtyRequested(jobResourceMasters.get(i).getQty());
					storeOrderResourceOutward.setOkflag('N');
					storeOrderResourceOutwardsCUDPublicRepo.save(storeOrderResourceOutward);
				}
			}
		}
	}

}