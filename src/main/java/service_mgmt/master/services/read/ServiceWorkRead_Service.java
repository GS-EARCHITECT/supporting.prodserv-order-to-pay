package service_mgmt.master.services.read;

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
import service_mgmt.master.model.dto.*;
import service_mgmt.master.model.repo.read.ServiceWorkMasterRead_Repo;
import service_mgmt.master.model.master.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("serviceWorkReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ServiceWorkRead_Service implements IServiceWorkRead_Service 
{
	//private static final Logger logger = LoggerFactory.getLogger(ServiceWork_Service.class);

	@Autowired
	private ServiceWorkMasterRead_Repo serviceWorkReadRepo;

	@Autowired
	private Executor asyncExecutor;

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getAllServiceWorks() 
	{
		CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceWorkMaster> servReqList = null;
			servReqList = (CopyOnWriteArrayList<ServiceWorkMaster>) serviceWorkReadRepo.findAll();
			CopyOnWriteArrayList<ServiceWorkMaster_DTO> lMasters = new CopyOnWriteArrayList<ServiceWorkMaster_DTO>();
			lMasters = servReqList != null ? this.getServWorkDtos(servReqList) : null;
			return lMasters;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorks(CopyOnWriteArrayList<Long> serviceReqSeqNos) 
	{
		CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceWorkMaster> lMasters = (CopyOnWriteArrayList<ServiceWorkMaster>) serviceWorkReadRepo.findAllById(serviceReqSeqNos);
			CopyOnWriteArrayList<ServiceWorkMaster_DTO> servMasterDTOs = lMasters != null ? this.getServWorkDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksByParties(CopyOnWriteArrayList<Long> pList) {
		CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceWorkMaster> lMasters = serviceWorkReadRepo.getSelectWorksByParties(pList);
			CopyOnWriteArrayList<ServiceWorkMaster_DTO> servMasterDTOs = lMasters != null ? this.getServWorkDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksByBookings(CopyOnWriteArrayList<Long> bList) 
	{
		CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceWorkMaster> lMasters = serviceWorkReadRepo.getSelectWorksByBookings(bList);
			CopyOnWriteArrayList<ServiceWorkMaster_DTO> servMasterDTOs = lMasters != null ? this.getServWorkDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	
	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksByRequests(CopyOnWriteArrayList<Long> rList) 
	{
		CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceWorkMaster> lMasters = serviceWorkReadRepo.getSelectWorksByRequests(rList);
			CopyOnWriteArrayList<ServiceWorkMaster_DTO> servMasterDTOs = lMasters != null ? this.getServWorkDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksByServices(CopyOnWriteArrayList<Long> sList) {
		CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceWorkMaster> lMasters = serviceWorkReadRepo.getSelectWorksByServices(sList);
			CopyOnWriteArrayList<ServiceWorkMaster_DTO> servMasterDTOs = lMasters != null ? this.getServWorkDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}
	
	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksByCreatedBy(CopyOnWriteArrayList<Long> cList) 
	{
		CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceWorkMaster> lMasters = serviceWorkReadRepo.getSelectWorksByCreatedBy(cList);
			CopyOnWriteArrayList<ServiceWorkMaster_DTO> servMasterDTOs = lMasters != null ? this.getServWorkDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksBetweenTimes(String frDtTm,
			String toDtTm) {
		CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
			LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
			Timestamp frTs = Timestamp.valueOf(frDTTm);
			Timestamp toTs = Timestamp.valueOf(toDTTm);
			CopyOnWriteArrayList<ServiceWorkMaster> lMasters = serviceWorkReadRepo.getSelectWorksBetweenTimes(frTs, toTs);
			CopyOnWriteArrayList<ServiceWorkMaster_DTO> servMasterDTOs = lMasters != null ? this.getServWorkDtos(lMasters)
					: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksBillPending()
			{
		CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceWorkMaster> lMasters = serviceWorkReadRepo.getSelectWorksBillPending();
			CopyOnWriteArrayList<ServiceWorkMaster_DTO> servMasterDTOs = lMasters != null ? this.getServWorkDtos(lMasters)	: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}
	
	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksForAutoAllocJobsNotAllocated()
			{
		CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceWorkMaster> lMasters = serviceWorkReadRepo.getSelectWorksForAutoAllocJobsNotAllocated();
			CopyOnWriteArrayList<ServiceWorkMaster_DTO> servMasterDTOs = lMasters != null ? this.getServWorkDtos(lMasters)	: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}
	
	@Override
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksForAutoAllocResourcesNotAllocated()
			{
		CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<ServiceWorkMaster> lMasters = serviceWorkReadRepo.getSelectWorksForAutoAllocResourcesNotAllocated();
			CopyOnWriteArrayList<ServiceWorkMaster_DTO> servMasterDTOs = lMasters != null ? this.getServWorkDtos(lMasters)	: null;
			return servMasterDTOs;
		}, asyncExecutor);

		return future;
	}

	
	private synchronized CopyOnWriteArrayList<ServiceWorkMaster_DTO> getServWorkDtos(
			CopyOnWriteArrayList<ServiceWorkMaster> servReqMasters) {
		CopyOnWriteArrayList<ServiceWorkMaster_DTO> serviceWorkDTOs = new CopyOnWriteArrayList<ServiceWorkMaster_DTO>();
		for (int i = 0; i < servReqMasters.size(); i++) {
			serviceWorkDTOs.add(this.getServiceWorkMaster_DTO(servReqMasters.get(i)));
		}
		return serviceWorkDTOs;
	}

	private synchronized ServiceWorkMaster_DTO getServiceWorkMaster_DTO(ServiceWorkMaster servwMaster) {
		ServiceWorkMaster_DTO serviceWorkDTO = new ServiceWorkMaster_DTO();		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		serviceWorkDTO.setOnDate(formatter.format(servwMaster.getOnDate().toLocalDateTime()));
		serviceWorkDTO.setAutoAllocStatus(servwMaster.getAutoAllocStatus());
		serviceWorkDTO.setCreatedBy(servwMaster.getCreatedBy());
		serviceWorkDTO.setPartySeqNo(servwMaster.getPartySeqNo());
		serviceWorkDTO.setBillingCurrencySeqNo(servwMaster.getBillingCurrencySeqNo());
		serviceWorkDTO.setBookingSeqNo(servwMaster.getBookingSeqNo());
		serviceWorkDTO.setJobAllocStatus(servwMaster.getJobAllocStatus());
		serviceWorkDTO.setMembershipSeqNo(servwMaster.getMembershipSeqNo());
		serviceWorkDTO.setParentServiceWorkSeqNo(servwMaster.getParentServiceWorkSeqNo());
		serviceWorkDTO.setRequestSeqNo(servwMaster.getRequestSeqNo());
		serviceWorkDTO.setResAllocStatus(servwMaster.getResAllocStatus());
		serviceWorkDTO.setResDirectIndirectFlag(servwMaster.getResDirectIndirectFlag());
		serviceWorkDTO.setServiceSeqNo(servwMaster.getServiceSeqNo());
		serviceWorkDTO.setServiceWorkSeqNo(servwMaster.getServiceWorkSeqNo());
		serviceWorkDTO.setBilledflag(servwMaster.getBilledflag());
		serviceWorkDTO.setOkflag(servwMaster.getOkflag());
		serviceWorkDTO.setDoneflag(servwMaster.getDoneflag());
		serviceWorkDTO.setJobAllocStatus(servwMaster.getJobAllocStatus());
		serviceWorkDTO.setResAllocStatus(servwMaster.getResAllocStatus());
		serviceWorkDTO.setJobautoflag(servwMaster.getJobautoflag());
		serviceWorkDTO.setResautoflag(servwMaster.getResautoflag());
		serviceWorkDTO.setRemark(servwMaster.getRemark());		
		return serviceWorkDTO;
	}
	
}