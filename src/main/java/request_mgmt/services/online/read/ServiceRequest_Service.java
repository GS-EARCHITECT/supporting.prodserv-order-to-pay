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
import request_mgmt.model.dto.ServiceRequestMaster_DTO;
import request_mgmt.model.master.ServiceRequestMaster;
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

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getAllServiceRequests() {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceRequestMaster> servReqList = (CopyOnWriteArrayList<ServiceRequestMaster>) serviceRequestReadRepo
					.findAll();
			CopyOnWriteArrayList<ServiceRequestMaster_DTO> lMasters = new CopyOnWriteArrayList<ServiceRequestMaster_DTO>();
			lMasters = servReqList != null ? this.getServRequestDtos(servReqList) : null;
			return lMasters;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequests(
			CopyOnWriteArrayList<Long> serviceReqSeqNos) {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceRequestMaster> lMasters = serviceRequestReadRepo
					.getSelectRequests(serviceReqSeqNos);
			CopyOnWriteArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null
					? this.getServRequestDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByParties(
			CopyOnWriteArrayList<Long> pList) {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceRequestMaster> lMasters = serviceRequestReadRepo
					.getSelectRequestsByParties(pList);
			CopyOnWriteArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null
					? this.getServRequestDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByRefList(
			CopyOnWriteArrayList<Long> rList) {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceRequestMaster> lMasters = serviceRequestReadRepo
					.getSelectRequestsByRefList(rList);
			CopyOnWriteArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null
					? this.getServRequestDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByFrLocs(
			CopyOnWriteArrayList<Long> lList) {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceRequestMaster> lMasters = serviceRequestReadRepo
					.getSelectRequestsByFrLocs(lList);
			CopyOnWriteArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null
					? this.getServRequestDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByToLocs(
			CopyOnWriteArrayList<Long> tList) {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceRequestMaster> lMasters = serviceRequestReadRepo
					.getSelectRequestsByToLocs(tList);
			CopyOnWriteArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null
					? this.getServRequestDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsOk() {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceRequestMaster> lMasters = serviceRequestReadRepo.getSelectRequestsOk();
			CopyOnWriteArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null
					? this.getServRequestDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsNotOk() {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceRequestMaster> lMasters = serviceRequestReadRepo.getSelectRequestsNotOk();
			CopyOnWriteArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null
					? this.getServRequestDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsDone() {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceRequestMaster> lMasters = serviceRequestReadRepo.getSelectRequestsDone();
			CopyOnWriteArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null
					? this.getServRequestDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsNotDone() {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceRequestMaster> lMasters = serviceRequestReadRepo.getSelectRequestsNotDone();
			CopyOnWriteArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null
					? this.getServRequestDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsFrPartyLat(Float lat) {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceRequestMaster> lMasters = serviceRequestReadRepo
					.getSelectRequestsFrPartyLat(lat);
			CopyOnWriteArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null
					? this.getServRequestDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsFrPartyLon(Float lat) {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceRequestMaster> lMasters = serviceRequestReadRepo
					.getSelectRequestsFrPartyLon(lat);
			CopyOnWriteArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null
					? this.getServRequestDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsBySuppliers(
			CopyOnWriteArrayList<Long> sList) {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceRequestMaster> lMasters = serviceRequestReadRepo
					.getSelectRequestsBySuppliers(sList);
			CopyOnWriteArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null
					? this.getServRequestDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsBetweenTimes(
			String frDtTm, String toDtTm) {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
			LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
			Timestamp frTs = Timestamp.valueOf(frDTTm);
			Timestamp toTs = Timestamp.valueOf(toDTTm);
			CopyOnWriteArrayList<ServiceRequestMaster> lMasters = serviceRequestReadRepo
					.getSelectRequestsBetweenTimes(frTs, toTs);
			CopyOnWriteArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null
					? this.getServRequestDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	private synchronized CopyOnWriteArrayList<ServiceRequestMaster_DTO> getServRequestDtos(
			CopyOnWriteArrayList<ServiceRequestMaster> servReqMasters) {
		CopyOnWriteArrayList<ServiceRequestMaster_DTO> serviceRequestDTOs = new CopyOnWriteArrayList<ServiceRequestMaster_DTO>();
		for (int i = 0; i < servReqMasters.size(); i++) {
			serviceRequestDTOs.add(this.getServiceRequestMaster_DTO(servReqMasters.get(i)));
		}
		return serviceRequestDTOs;
	}

	private synchronized ServiceRequestMaster_DTO getServiceRequestMaster_DTO(ServiceRequestMaster servReqMaster) {
		ServiceRequestMaster_DTO serviceRequestDTO = new ServiceRequestMaster_DTO();
		serviceRequestDTO.setRequestSeqNo(servReqMaster.getRequestSeqNo());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		serviceRequestDTO.setDoneFlag(serviceRequestDTO.getDoneFlag());
		serviceRequestDTO.setFrPartyLat(serviceRequestDTO.getFrPartyLat());
		serviceRequestDTO.setFrPartyLocationSeqNo(serviceRequestDTO.getFrPartyLocationSeqNo());
		serviceRequestDTO.setFrPartyLon(serviceRequestDTO.getFrPartyLon());
		serviceRequestDTO.setFrPartySeqNo(serviceRequestDTO.getFrPartySeqNo());
		serviceRequestDTO.setOkFlag(serviceRequestDTO.getOkFlag());
		serviceRequestDTO.setReferenceSeqNo(serviceRequestDTO.getReferenceSeqNo());
		serviceRequestDTO.setRemark(serviceRequestDTO.getRemark());
		serviceRequestDTO.setRequestSeqNo(serviceRequestDTO.getRequestSeqNo());
		serviceRequestDTO.setStatus(serviceRequestDTO.getStatus());
		serviceRequestDTO.setToPartyLocationSeqNo(serviceRequestDTO.getToPartyLocationSeqNo());
		serviceRequestDTO.setToPartySeqNo(serviceRequestDTO.getToPartySeqNo());
		serviceRequestDTO.setRequestDttm(formatter.format(servReqMaster.getRequestDttm().toLocalDateTime()));
		return serviceRequestDTO;
	}

}