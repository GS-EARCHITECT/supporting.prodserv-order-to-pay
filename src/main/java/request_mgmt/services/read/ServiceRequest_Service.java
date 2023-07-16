package request_mgmt.services.read;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import request_mgmt.model.dto.ServiceRequestMaster_DTO;
import request_mgmt.model.dto.ServiceRequestStatusDetails_DTO;
import request_mgmt.model.master.ServiceRequestMaster;
import request_mgmt.model.master.ServiceRequestStatusDetails;
import request_mgmt.model.repo.read.ServiceRequestMasterRead_Repo;
import request_mgmt.model.repo.read.ServiceRequestStatusDetailsRead_Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("serviceRequestReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ServiceRequest_Service implements IServiceRequestRead_Service {
	private static final Logger logger = LoggerFactory.getLogger(ServiceRequest_Service.class);

	@Autowired
	private ServiceRequestMasterRead_Repo serviceRequestReadRepo;

	@Autowired
	private Executor asyncExecutor;

	@Autowired
	private ServiceRequestStatusDetailsRead_Repo serviceRequestStatusReadRepo;

	/* SERVICE REQUEST MASTER */

	@Override
	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getAllServiceRequests() {
		CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			ArrayList<ServiceRequestMaster> servReqList = null;
			servReqList = (ArrayList<ServiceRequestMaster>) serviceRequestReadRepo.findAll();
			ArrayList<ServiceRequestMaster_DTO> lMasters = new ArrayList<ServiceRequestMaster_DTO>();
			lMasters = servReqList != null ? this.getServRequestDtos(servReqList) : null;
			return lMasters;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getSelectRequests(ArrayList<Long> serviceReqSeqNos) {
		CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			ArrayList<ServiceRequestMaster> lMasters = serviceRequestReadRepo.getSelectRequests(serviceReqSeqNos);
			ArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null ? this.getServRequestDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByCompanies(ArrayList<Long> cList) {
		CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			ArrayList<ServiceRequestMaster> lMasters = serviceRequestReadRepo.getSelectRequestsByCompanies(cList);
			ArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null ? this.getServRequestDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByPeople(ArrayList<Long> pList) {
		CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			ArrayList<ServiceRequestMaster> lMasters = serviceRequestReadRepo.getSelectRequestsByPeople(pList);
			ArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null ? this.getServRequestDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsBySuppliers(ArrayList<Long> sList) {
		CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			ArrayList<ServiceRequestMaster> lMasters = serviceRequestReadRepo.getSelectRequestsBySuppliers(sList);
			ArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null ? this.getServRequestDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsBetweenTimes(String frDtTm,
			String toDtTm) {
		CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
			LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
			Timestamp frTs = Timestamp.valueOf(frDTTm);
			Timestamp toTs = Timestamp.valueOf(toDTTm);
			ArrayList<ServiceRequestMaster> lMasters = serviceRequestReadRepo.getSelectRequestsBetweenTimes(frTs, toTs);
			ArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null ? this.getServRequestDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	private synchronized ArrayList<ServiceRequestMaster_DTO> getServRequestDtos(
			ArrayList<ServiceRequestMaster> servReqMasters) {
		ArrayList<ServiceRequestMaster_DTO> serviceRequestDTOs = new ArrayList<ServiceRequestMaster_DTO>();
		for (int i = 0; i < servReqMasters.size(); i++) {
			serviceRequestDTOs.add(this.getServiceRequestMaster_DTO(servReqMasters.get(i)));
		}
		return serviceRequestDTOs;
	}

	private synchronized ServiceRequestMaster_DTO getServiceRequestMaster_DTO(ServiceRequestMaster servReqMaster) {
		ServiceRequestMaster_DTO serviceRequestDTO = new ServiceRequestMaster_DTO();
		serviceRequestDTO.setRequestSeqNo(servReqMaster.getRequestSeqNo());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		serviceRequestDTO.setRequestDate(formatter.format(servReqMaster.getRequestDate().toLocalDateTime()));
		serviceRequestDTO.setPersonSeqNo(servReqMaster.getPersonSeqNo());
		serviceRequestDTO.setCompanySeqNo(servReqMaster.getCompanySeqNo());
		serviceRequestDTO.setSupplierSeqNo(servReqMaster.getSupplierSeqNo());
		serviceRequestDTO.setStatus(servReqMaster.getStatus());
		serviceRequestDTO.setRemark(servReqMaster.getRemark());
		return serviceRequestDTO;
	}

	/* SERVICE REQUEST STATUS */

	public CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> getSelectRequestStatus(ArrayList<Long> rList) {
		CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> future = CompletableFuture.supplyAsync(() -> {
			ArrayList<ServiceRequestStatusDetails> lMasters = new ArrayList<ServiceRequestStatusDetails>();
			lMasters = serviceRequestStatusReadRepo.getSelectRequestStatus(rList);
			ArrayList<ServiceRequestStatusDetails_DTO> servMasterDTOs = lMasters != null
					? this.getServRequestStatusDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> getSelectRequestStatusBetweenTimes(
			String frDtTm, String toDtTm) {
		CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> future = CompletableFuture.supplyAsync(() -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
			LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
			Timestamp frTs = Timestamp.valueOf(frDTTm);
			Timestamp toTs = Timestamp.valueOf(toDTTm);
			ArrayList<ServiceRequestStatusDetails> lMasters = serviceRequestStatusReadRepo
					.getSelectRequestStatusBetweenTimes(frTs, toTs);
			ArrayList<ServiceRequestStatusDetails_DTO> servMasterDTOs = lMasters != null
					? this.getServRequestStatusDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> getAllServiceStatusDetails() {
		CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> future = CompletableFuture.supplyAsync(() -> {
			ArrayList<ServiceRequestStatusDetails> lMasters = (ArrayList<ServiceRequestStatusDetails>) serviceRequestStatusReadRepo
					.findAll();
			ArrayList<ServiceRequestStatusDetails_DTO> servMasterDTOs = lMasters != null
					? this.getServRequestStatusDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);
		return future;
	}

	private synchronized ArrayList<ServiceRequestStatusDetails_DTO> getServRequestStatusDtos(
			ArrayList<ServiceRequestStatusDetails> servReqStatusDetails) {
		ArrayList<ServiceRequestStatusDetails_DTO> serviceRequestStatusDTOs = new ArrayList<ServiceRequestStatusDetails_DTO>();

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