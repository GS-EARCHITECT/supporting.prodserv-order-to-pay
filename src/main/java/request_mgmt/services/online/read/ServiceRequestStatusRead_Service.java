package request_mgmt.services.online.read;

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
import request_mgmt.model.dto.ServiceRequestStatusDetails_DTO;
import request_mgmt.model.master.ServiceRequestStatusDetails;
import request_mgmt.model.master.ServiceRequestStatusDetailsPK;
import request_mgmt.model.repo.read.ServiceRequestStatusDetailsRead_Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("serviceRequestStatusReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ServiceRequestStatusRead_Service implements IServiceRequestStatusRead_Service 
{
	private static final Logger logger = LoggerFactory.getLogger(ServiceRequestStatusRead_Service.class);

	@Autowired
	private Executor asyncExecutor;

	@Autowired
	private ServiceRequestStatusDetailsRead_Repo serviceRequestStatusReadRepo;

	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO>> getSelectRequestStatus(
			CopyOnWriteArrayList<ServiceRequestStatusDetailsPK> sList) {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO>> future = CompletableFuture
				.supplyAsync(() -> {
					CopyOnWriteArrayList<ServiceRequestStatusDetails> lMasters = (CopyOnWriteArrayList<ServiceRequestStatusDetails>) serviceRequestStatusReadRepo.findAllById(sList);
					CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO> servMasterDTOs = lMasters != null ? this.getServRequestStatusDtos(lMasters)
							: null;
					return servMasterDTOs;
				}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO>> getSelectRequestStatusBetweenTimes(
			String frDtTm, String toDtTm) {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO>> future = CompletableFuture
				.supplyAsync(() -> {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
					LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
					LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
					Timestamp frTs = Timestamp.valueOf(frDTTm);
					Timestamp toTs = Timestamp.valueOf(toDTTm);
					CopyOnWriteArrayList<ServiceRequestStatusDetails> lMasters = serviceRequestStatusReadRepo
							.getSelectRequestStatusBetweenTimes(frTs, toTs);
					CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO> servMasterDTOs = lMasters != null
							? this.getServRequestStatusDtos(lMasters)
							: null;
					return servMasterDTOs;
				}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO>> getAllServiceStatusDetails() {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO>> future = CompletableFuture
				.supplyAsync(() -> {
					CopyOnWriteArrayList<ServiceRequestStatusDetails> lMasters = (CopyOnWriteArrayList<ServiceRequestStatusDetails>) serviceRequestStatusReadRepo
							.findAll();
					CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO> servMasterDTOs = lMasters != null
							? this.getServRequestStatusDtos(lMasters)
							: null;
					return servMasterDTOs;
				}, asyncExecutor);
		return future;
	}

	private synchronized CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO> getServRequestStatusDtos(
			CopyOnWriteArrayList<ServiceRequestStatusDetails> servReqStatusDetails) {
		CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO> serviceRequestStatusDTOs = new CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO>();

		for (int i = 0; i < servReqStatusDetails.size(); i++) {
			serviceRequestStatusDTOs.add(this.getServiceRequestStatusDTO(servReqStatusDetails.get(i)));
		}
		return serviceRequestStatusDTOs;
	}

	private synchronized ServiceRequestStatusDetails_DTO getServiceRequestStatusDTO(
			ServiceRequestStatusDetails servReqStatusDetails) {
		ServiceRequestStatusDetails_DTO serviceRequestStatusDTO = new ServiceRequestStatusDetails_DTO();
		serviceRequestStatusDTO.setRequestSeqNo(servReqStatusDetails.getId().getRequestSeqNo());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		serviceRequestStatusDTO.setOnDate(formatter.format(servReqStatusDetails.getId().getOnDate().toLocalDateTime()));
		serviceRequestStatusDTO.setRemark(servReqStatusDetails.getRemark());
		serviceRequestStatusDTO.setStatus(servReqStatusDetails.getStatus());
		return serviceRequestStatusDTO;
	}

}