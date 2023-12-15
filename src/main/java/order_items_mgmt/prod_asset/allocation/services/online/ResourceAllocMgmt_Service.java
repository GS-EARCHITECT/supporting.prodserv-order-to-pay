package order_items_mgmt.prod_asset.allocation.services.online;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import common.model.master.ServiceWorkMaster;
import order_items_mgmt.prod_asset.allocation.model.repo.common.*;
import order_items_mgmt.prod_asset.allocation.model.repo.scheduler.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import common.model.master.*;

@Service("resourceAllocMgmtServOnLine")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ResourceAllocMgmt_Service implements I_ResourceAllocMgmt_Service {

	private static final Logger logger = LoggerFactory.getLogger(ResourceAllocMgmt_Service.class);

	@Autowired
	private JobWorkDetailsRead_Repo jobWorkDetailsReadRepoForResources;

	@Autowired
	private JobTripRepo jobTripRepoOnLineForResources;

	@Autowired
	private StoreOrderAssetInwardsCUDPublic_Repo storeOrderAssetInwardsCUDPublicRepoOnLine;

	@Autowired
	private StoreOrderResourceInwardsCUDPublic_Repo storeOrderResourceInwardsCUDPublicRepoOnLine;

	@Autowired
	private StoreOrderResourceOutwardsCUDPublic_Repo storeOrderResourceOutwardsCUDPublicRepoOnLine;

	@Autowired
	private StoreOrderAssetOutwardsCUDPublic_Repo storeOrderAssetOutwardsCUDPublicRepoOnLine;

	@Autowired
	private JobAssetMaster_Repo jobAssetMasterRepo;

	@Autowired
	private Executor asyncExecutor;

	@Autowired
	private JobResourceMaster_Repo jobResourceMasterRepo;

	@Autowired
	private ServiceWorkMasterCUD_Repo serviceWorkCUDRepoOnLineForResources;

	@Autowired
	private ServiceWorkMasterRead_Repo serviceWorkReadRepoForResources;

	/* Online Resource Alloc */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public CompletableFuture<Void> resource_OnlineAlloc(Long servWorkSeqNo) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			Optional<ServiceWorkMaster> workServs = serviceWorkReadRepoForResources.findById(servWorkSeqNo);
			CopyOnWriteArrayList<JobWorkDetail> jobsList = null;
			jobsList = jobWorkDetailsReadRepoForResources
					.getSelectJobWorkDetailForService(workServs.get().getServiceWorkSeqNo());

			if (jobsList != null && jobsList.size() > 0) {
				logger.info("found work");
				doAlloc(jobsList);
				serviceWorkCUDRepoOnLineForResources.updResourceAllocStatus(servWorkSeqNo, 'Y');
			}
			return;
		}, asyncExecutor);
		return future;
	}

	public synchronized void doAlloc(CopyOnWriteArrayList<JobWorkDetail> jobsList) 
	{
		CopyOnWriteArrayList<JobTripDetails> jobTrips = null;
		Long jobWorkSeqNo = (long) 0;
		Long jobSeqNo = null;
		Long targetSeqNo = (long) 0;
		Timestamp pl_start, pl_end, ac_start, ac_end;

		// For each job for service work in job details
		for (int j = 0; j < jobsList.size(); j++) {
			logger.info("job work :" + jobsList.get(j).getJobWorkSeqNo());
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
					jobTrips = jobTripRepoOnLineForResources.getJobActualDetails(jobWorkSeqNo);
				} else {
					jobTrips = jobTripRepoOnLineForResources.getJobPlanDetails(jobWorkSeqNo);
				}

				if (jobTrips != null && jobTrips.size() > 0) {
					for (int j7 = 0; j7 < jobTrips.size(); j7++) {

						if (ac_start != null && ac_end != null) {
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
						allocate_Assets(jobWorkSeqNo, jobSeqNo, 0, targetSeqNo, (long) 0, (long) 0, pl_start, pl_end);
					}
					else 
					{
						allocate_Assets(jobWorkSeqNo, jobSeqNo, 0, targetSeqNo, (long) 0, (long) 0, ac_start, ac_end);
					}

					if (ac_start != null && ac_end != null) 
					{
						allocate_Resources(jobWorkSeqNo, jobSeqNo, 0, targetSeqNo, (long) 0, (long) 0, pl_start,
								pl_end);
					} else {
						allocate_Resources(jobWorkSeqNo, jobSeqNo, 0, targetSeqNo, (long) 0, (long) 0, ac_start,
								ac_end);
					}

				}

			}
		}
	}

	public synchronized void allocate_Assets(Long jobWorkSeqNo, Long jobSeqNo, Integer modeSeqNo, Long targetSeqNo,
			Long frLocSeqNo, Long toLocSeqNo, Timestamp frDtTm, Timestamp toDtTm) 
	{
		logger.info("assets check for :"+jobWorkSeqNo);
		storeOrderAssetInwardsCUDPublicRepoOnLine.markDeleteByJob(jobWorkSeqNo);
		storeOrderAssetOutwardsCUDPublicRepoOnLine.markDeleteByJob(jobWorkSeqNo);
		
		CopyOnWriteArrayList<JobAssetMaster> jobAssetMasters = null;
		StoreOrderAssetInward storeOrderAssetInward = null;
		StoreOrderAssetOutward storeOrderAssetOutward = null;
		jobAssetMasters = jobAssetMasterRepo.getAssetsForJobType(jobSeqNo, targetSeqNo, modeSeqNo, frLocSeqNo,	toLocSeqNo);

		if (jobAssetMasters != null && jobAssetMasters.size() > 0) 
		{
			
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
					storeOrderAssetInwardsCUDPublicRepoOnLine.save(storeOrderAssetInward);
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
					storeOrderAssetOutwardsCUDPublicRepoOnLine.save(storeOrderAssetOutward);
				}
			}
		}
	}

	public synchronized void allocate_Resources(Long jobWorkSeqNo, Long jobSeqNo, Integer modeSeqNo, Long targetSeqNo,
			Long frLocSeqNo, Long toLocSeqNo, Timestamp frDtTm, Timestamp toDtTm) {
		logger.info("res check for :"+jobWorkSeqNo);
		storeOrderResourceInwardsCUDPublicRepoOnLine.markDeleteByJob(jobWorkSeqNo);
		storeOrderResourceOutwardsCUDPublicRepoOnLine.markDeleteByJob(jobWorkSeqNo);
		CopyOnWriteArrayList<JobResourceMaster> jobResourceMasters = null;
		StoreOrderResourceInward storeOrderResourceInward = null;
		StoreOrderResourceOutward storeOrderResourceOutward = null;
		jobResourceMasters = jobResourceMasterRepo.getResourcesForJobType(jobSeqNo, targetSeqNo, modeSeqNo, frLocSeqNo,
				toLocSeqNo);

		if (jobResourceMasters != null && jobResourceMasters.size() > 0) {
			logger.info("found resources");
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
					storeOrderResourceInwardsCUDPublicRepoOnLine.save(storeOrderResourceInward);
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
					storeOrderResourceOutwardsCUDPublicRepoOnLine.save(storeOrderResourceOutward);
				}
			}
		}
	}

}