package service_mgmt.pax_details.services.read;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service_mgmt.pax_details.model.dto.ServiceWorkPaxDetail_DTO;
import service_mgmt.pax_details.model.master.ServiceWorkPaxDetail;
import service_mgmt.pax_details.model.repo.read.ServiceWorkPaxDetailsRead_Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("serviceWorkPaxReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ServiceWorkPaxDetailsRead_Service implements IServiceWorkPaxDetailsRead_Service 
{
//	private static final Logger logger = LoggerFactory.getLogger(ServiceWorkPax_Service.class);

	@Autowired
	private ServiceWorkPaxDetailsRead_Repo serviceWorkPaxDetailsReadRepo;

	@Autowired
	private Executor asyncExecutor;

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkPaxDetail_DTO>> getAllServiceWorkPaxs() {
		CompletableFuture<CopyOnWriteArrayList<ServiceWorkPaxDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceWorkPaxDetail> servReqList = null;
			servReqList = (CopyOnWriteArrayList<ServiceWorkPaxDetail>) serviceWorkPaxDetailsReadRepo.findAll();
			CopyOnWriteArrayList<ServiceWorkPaxDetail_DTO> lDetails = new CopyOnWriteArrayList<ServiceWorkPaxDetail_DTO>();
			lDetails = servReqList != null ? this.getServWorkPaxDtos(servReqList) : null;
			return lDetails;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkPaxDetail_DTO>> getSelectWorkPaxs(CopyOnWriteArrayList<Long> serviceReqSeqNos) {
		CompletableFuture<CopyOnWriteArrayList<ServiceWorkPaxDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceWorkPaxDetail> lDetails = serviceWorkPaxDetailsReadRepo.getSelectWorkPaxs(serviceReqSeqNos);
			CopyOnWriteArrayList<ServiceWorkPaxDetail_DTO> servDetailDTOs = lDetails != null ? this.getServWorkPaxDtos(lDetails)
					: null;
			return servDetailDTOs;
		}, asyncExecutor);

		return future;
	}

	private synchronized CopyOnWriteArrayList<ServiceWorkPaxDetail_DTO> getServWorkPaxDtos(
			CopyOnWriteArrayList<ServiceWorkPaxDetail> servReqDetails) {
		CopyOnWriteArrayList<ServiceWorkPaxDetail_DTO> serviceWorkPaxDTOs = new CopyOnWriteArrayList<ServiceWorkPaxDetail_DTO>();
		for (int i = 0; i < servReqDetails.size(); i++) {
			serviceWorkPaxDTOs.add(this.getServiceWorkPaxDetail_DTO(servReqDetails.get(i)));
		}
		return serviceWorkPaxDTOs;
	}

	private synchronized ServiceWorkPaxDetail_DTO getServiceWorkPaxDetail_DTO(ServiceWorkPaxDetail servReqDetail) {
		ServiceWorkPaxDetail_DTO serviceWorkPaxDTO = new ServiceWorkPaxDetail_DTO();
		serviceWorkPaxDTO.setPartySeqNo(servReqDetail.getId().getPartySeqNo());
		serviceWorkPaxDTO.setServiceWorkSeqNo(servReqDetail.getId().getServiceWorkSeqNo());
		serviceWorkPaxDTO.setStatus(servReqDetail.getStatus());
		serviceWorkPaxDTO.setRemark(servReqDetail.getRemark());
		return serviceWorkPaxDTO;
	}
	
}