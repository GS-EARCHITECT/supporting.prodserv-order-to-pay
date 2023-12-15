package jobs.job_target_details.services;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import common.model.dto.JobTargetDetail_DTO;
import common.model.master.JobTargetDetail;
import common.model.master.JobTargetDetailPK;
import jobs.job_target_details.model.repo.JobTargetDetailsRead_Repo;

@Service("jobTargetDetailsReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class JobTargetDetailsRead_Service implements I_JobTargetDetailsRead_Service 
{

	// private static final Logger logger =
	// LoggerFactory.getLogger(JobTargetDetailService.class);

	@Autowired
	private JobTargetDetailsRead_Repo jobTargetDetailsReadRepo;

	@Autowired
	private Executor asyncExecutor;

	@Override
	public CompletableFuture<CopyOnWriteArrayList<JobTargetDetail_DTO>> getAllJobTargetDetails()
			throws InterruptedException, ExecutionException {
		CompletableFuture<CopyOnWriteArrayList<JobTargetDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<JobTargetDetail> jobList = (CopyOnWriteArrayList<JobTargetDetail>) jobTargetDetailsReadRepo
					.findAll();
			CopyOnWriteArrayList<JobTargetDetail_DTO> jobTargetDetailDTOs = new CopyOnWriteArrayList<JobTargetDetail_DTO>();
			jobTargetDetailDTOs = jobList != null ? this.getJobTargetDetail_DTOs(jobList) : null;
			return jobTargetDetailDTOs;
		}, asyncExecutor);

		return future;

	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<JobTargetDetail_DTO>> getAllJobTargetDetailsByIds(CopyOnWriteArrayList<JobTargetDetailPK> jobTargetDetailsPks) throws InterruptedException, ExecutionException 
	{
		CompletableFuture<CopyOnWriteArrayList<JobTargetDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<JobTargetDetail> jobTargetDetails = null;
			CopyOnWriteArrayList<JobTargetDetail_DTO> jobTargetDetailDTOs = null;
			if (jobTargetDetailsPks != null) {
				jobTargetDetails = (CopyOnWriteArrayList<JobTargetDetail>) jobTargetDetailsReadRepo
						.findAllById(jobTargetDetailsPks);
				if (jobTargetDetails != null) {
					jobTargetDetailDTOs = this.getJobTargetDetail_DTOs(jobTargetDetails);
				}
			}
			return jobTargetDetailDTOs;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<JobTargetDetail_DTO>> getSelectJobTargetDetails(
			CopyOnWriteArrayList<Long> jobTargetDetailsSeqNos) throws InterruptedException, ExecutionException {
		CompletableFuture<CopyOnWriteArrayList<JobTargetDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<JobTargetDetail> jobTargetDetails = null;
			CopyOnWriteArrayList<JobTargetDetail_DTO> jobTargetDetailDTOs = null;
			if (jobTargetDetailsSeqNos != null) {
				jobTargetDetails = (CopyOnWriteArrayList<JobTargetDetail>) jobTargetDetailsReadRepo
						.getSelectJobTargetDetails(jobTargetDetailsSeqNos);
				if (jobTargetDetails != null) {
					jobTargetDetailDTOs = this.getJobTargetDetail_DTOs(jobTargetDetails);
				}
			}
			return jobTargetDetailDTOs;
		}, asyncExecutor);

		return future;

	}

	@Override
	public CompletableFuture<Float> getJobDurDays(Long jobSeqNo, Long trgSeqNo) throws InterruptedException, ExecutionException {
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> {
			Float jobDuration = (float) 0;

			{
				jobDuration = jobTargetDetailsReadRepo.getJobDurDays(jobSeqNo, trgSeqNo);
			}
			return jobDuration;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<Float> getJobDurHours(Long jobSeqNo, Long trgSeqNo) throws InterruptedException, ExecutionException {
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> {
			Float jobDuration = (float) 0;

			if (jobSeqNo != null) {
				jobDuration = jobTargetDetailsReadRepo.getJobDurHours(jobSeqNo, trgSeqNo);
			}
			return jobDuration;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<Float> getJobDurSeconds(Long jobSeqNo, Long trgSeqNo) throws InterruptedException, ExecutionException {
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> {
			Float jobDuration = (float) 0;

			if (jobSeqNo != null) {
				jobDuration = jobTargetDetailsReadRepo.getJobDurSeconds(jobSeqNo, trgSeqNo);
			}
			return jobDuration;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<Float> getJobDurMinutes(Long jobSeqNo, Long trgSeqNo) throws InterruptedException, ExecutionException {
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> {
			Float jobDuration = (float) 0;

			if (jobSeqNo != null) {
				jobDuration = jobTargetDetailsReadRepo.getJobDurMinutes(jobSeqNo, trgSeqNo);
			}
			return jobDuration;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<Float> getJobDurMonths(Long jobSeqNo, Long trgSeqNo) throws InterruptedException, ExecutionException {
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> {
			Float jobDuration = (float) 0;

			if (jobSeqNo != null) {
				jobDuration = jobTargetDetailsReadRepo.getJobDurMonths(jobSeqNo, trgSeqNo);
			}
			return jobDuration;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<Float> getJobDurWeeks(Long jobSeqNo, Long trgSeqNo) throws InterruptedException, ExecutionException {
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> {
			Float jobDuration = (float) 0;

			if (jobSeqNo != null) {
				jobDuration = jobTargetDetailsReadRepo.getJobDurWeeks(jobSeqNo, trgSeqNo);
			}
			return jobDuration;
		}, asyncExecutor);

		return future;
	}

	private synchronized CopyOnWriteArrayList<JobTargetDetail_DTO> getJobTargetDetail_DTOs(
			CopyOnWriteArrayList<JobTargetDetail> jobTargetDetails) {
		JobTargetDetail_DTO jobTargetDetailDTO = null;
		CopyOnWriteArrayList<JobTargetDetail_DTO> jobTargetDetailDTOs = new CopyOnWriteArrayList<JobTargetDetail_DTO>();

		for (int i = 0; i < jobTargetDetails.size(); i++) {
			jobTargetDetailDTO = getJobTargetDetail_DTO(jobTargetDetails.get(i));
			jobTargetDetailDTOs.add(jobTargetDetailDTO);
		}
		return jobTargetDetailDTOs;
	}

	private JobTargetDetail_DTO getJobTargetDetail_DTO(JobTargetDetail jobTargetDetails) {
		JobTargetDetail_DTO jobTargetDetailDTO = new JobTargetDetail_DTO();
		jobTargetDetailDTO = new JobTargetDetail_DTO();
		jobTargetDetailDTO.setJobSeqNo(jobTargetDetails.getId().getJobSeqNo());
		jobTargetDetailDTO.setTargetSeqNo(jobTargetDetails.getId().getTargetSeqNo());
		jobTargetDetailDTO.setDuration(jobTargetDetails.getDuration());
		jobTargetDetailDTO.setDurationSeqNo(jobTargetDetails.getDurationSeqNo());
		jobTargetDetailDTO.setDurMonths(jobTargetDetails.getDurMonths());
		jobTargetDetailDTO.setDurWeeks(jobTargetDetails.getDurWeeks());
		jobTargetDetailDTO.setDurDays(jobTargetDetails.getDurDays());
		jobTargetDetailDTO.setDurHours(jobTargetDetails.getDurHours());
		jobTargetDetailDTO.setDurMinutes(jobTargetDetails.getDurMinutes());
		jobTargetDetailDTO.setDurSeconds(jobTargetDetails.getDurSeconds());
		jobTargetDetailDTO.setRateSeqNo(jobTargetDetails.getRateSeqNo());
		jobTargetDetailDTO.setUnitRate(jobTargetDetails.getUnitRate());
		jobTargetDetailDTO.setRemarks(jobTargetDetails.getRemarks());
		jobTargetDetailDTO.setStatus(jobTargetDetails.getStatus());
		return jobTargetDetailDTO;
	}

}