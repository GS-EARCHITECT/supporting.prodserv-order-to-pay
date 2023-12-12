package service_mgmt.master.services.cud;

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
import service_mgmt.master.model.dto.ServiceWorkMaster_DTO;
import service_mgmt.master.model.master.ServiceWorkMaster;
import service_mgmt.master.model.repo.cud.ServiceWorkMasterCUD_Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("serviceWorkCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ServiceWorkCUD_Service implements IServiceWorkCUD_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(ServiceWork_Service.class);

	@Autowired
	private ServiceWorkMasterCUD_Repo serviceWorkCUDRepo;

	@Autowired
	private Executor asyncExecutor;

	@Override
	public CompletableFuture<ServiceWorkMaster_DTO> newServiceWork(ServiceWorkMaster_DTO srvwDTO) {
		CompletableFuture<ServiceWorkMaster_DTO> future = CompletableFuture.supplyAsync(() -> {
			ServiceWorkMaster_DTO serviceWorkDTO = new ServiceWorkMaster_DTO();
			if (!serviceWorkCUDRepo.existsById(srvwDTO.getServiceWorkSeqNo())) {
				ServiceWorkMaster ServiceWorkMaster = serviceWorkCUDRepo.save(this.setServiceWorkMaster(srvwDTO));
				serviceWorkDTO = this.getServiceWorkMaster_DTO(ServiceWorkMaster);
			}
			return serviceWorkDTO;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<Void> updServiceWork(ServiceWorkMaster_DTO servwDTO) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (serviceWorkCUDRepo.existsById(servwDTO.getServiceWorkSeqNo())) {
				ServiceWorkMaster ServiceWorkMaster = this.setServiceWorkMaster(servwDTO);
				ServiceWorkMaster.setServiceWorkSeqNo(servwDTO.getServiceWorkSeqNo());
				serviceWorkCUDRepo.save(ServiceWorkMaster);
			}
		}, asyncExecutor);
		return future;
	}

	@Override
	public CompletableFuture<Void> updResourceAllocStatus(Long id, Character st) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (serviceWorkCUDRepo.existsById(id)) {
				serviceWorkCUDRepo.updResourceAllocStatus(id, st);
			}
		}, asyncExecutor);
		return future;
	}

	@Override
	public CompletableFuture<Void> updResourceAutoFlag(Long id, Character fl) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (serviceWorkCUDRepo.existsById(id)) {
				serviceWorkCUDRepo.updResourceAutoFlag(id, fl);
			}
		}, asyncExecutor);
		return future;
	}

	@Override
	public CompletableFuture<Void> updJobAllocStatus(Long id, Character st) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (serviceWorkCUDRepo.existsById(id)) {
				serviceWorkCUDRepo.updJobAllocStatus(id, st);
			}
		}, asyncExecutor);
		return future;
	}

	@Override
	public CompletableFuture<Void> updJobAutoFlag(Long id, Character fl) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (serviceWorkCUDRepo.existsById(id)) {
				serviceWorkCUDRepo.updJobAutoFlag(id, fl);
			}
		}, asyncExecutor);
		return future;
	}

	@Override
	public CompletableFuture<Void> updOkFlag(Long id, Character fl) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (serviceWorkCUDRepo.existsById(id)) {
				serviceWorkCUDRepo.updOkFlag(id, fl);
			}
		}, asyncExecutor);
		return future;
	}

	@Override
	public CompletableFuture<Void> updDoneFlag(Long id, Character fl) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (serviceWorkCUDRepo.existsById(id)) {
				serviceWorkCUDRepo.updDoneFlag(id, fl);
			}
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> updSelectWorkBillStatus(Long id, Character st) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (serviceWorkCUDRepo.existsById(id)) {
				serviceWorkCUDRepo.updSelectWorkBillStatus(id, st);
			}
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectWorks(CopyOnWriteArrayList<Long> sList) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			serviceWorkCUDRepo.deleteAllById(sList);
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectWorksBillPending() {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			serviceWorkCUDRepo.delSelectWorksBillPending();
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectWorksByParties(CopyOnWriteArrayList<Long> pList) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			serviceWorkCUDRepo.delSelectWorksByParties(pList);
		}, asyncExecutor);
		return future;

	}

	public CompletableFuture<Void> delSelectWorksByBookings(CopyOnWriteArrayList<Long> bList) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			serviceWorkCUDRepo.delSelectWorksByBookings(bList);
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectWorksByRequests(CopyOnWriteArrayList<Long> rList) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			serviceWorkCUDRepo.delSelectWorksByRequests(rList);
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectWorksByServices(CopyOnWriteArrayList<Long> sList) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			serviceWorkCUDRepo.delSelectWorksByServices(sList);
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectWorksByCreatedBy(CopyOnWriteArrayList<Long> cList) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			serviceWorkCUDRepo.delSelectWorksByServices(cList);
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectWorksBetweenTimes(String frDtTm, String toDtTm) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
			LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
			Timestamp frTs = Timestamp.valueOf(frDTTm);
			Timestamp toTs = Timestamp.valueOf(toDTTm);
			serviceWorkCUDRepo.delSelectWorksBetweenTimes(frTs, toTs);
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectWorksForAutoAllocJobsNotAllocated() {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			serviceWorkCUDRepo.delSelectWorksForAutoAllocJobsNotAllocated();
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectWorksForAutoAllocResourcesNotAllocated() {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			serviceWorkCUDRepo.delSelectWorksForAutoAllocResourcesNotAllocated();
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delWorksDone() {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			serviceWorkCUDRepo.delWorksDone();
		}, asyncExecutor);
		return future;
	}

	@Override
	public CompletableFuture<Void> delAllServiceWorks() {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			serviceWorkCUDRepo.deleteAll();
		}, asyncExecutor);
		return future;
	}

	private synchronized ServiceWorkMaster_DTO getServiceWorkMaster_DTO(ServiceWorkMaster sWDTO) 
	{
		ServiceWorkMaster_DTO serviceWorkDTO = new ServiceWorkMaster_DTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		serviceWorkDTO.setOnDate(formatter.format(sWDTO.getOnDate().toLocalDateTime()));
		serviceWorkDTO.setAutoAllocStatus(sWDTO.getAutoAllocStatus());
		serviceWorkDTO.setCreatedBy(sWDTO.getCreatedBy());
		serviceWorkDTO.setPartySeqNo(sWDTO.getPartySeqNo());
		serviceWorkDTO.setBillingCurrencySeqNo(sWDTO.getBillingCurrencySeqNo());
		serviceWorkDTO.setBookingSeqNo(sWDTO.getBookingSeqNo());
		serviceWorkDTO.setJobAllocStatus(sWDTO.getJobAllocStatus());
		serviceWorkDTO.setMembershipSeqNo(sWDTO.getMembershipSeqNo());
		serviceWorkDTO.setParentServiceWorkSeqNo(sWDTO.getParentServiceWorkSeqNo());
		serviceWorkDTO.setRequestSeqNo(sWDTO.getRequestSeqNo());
		serviceWorkDTO.setResAllocStatus(sWDTO.getResAllocStatus());
		serviceWorkDTO.setResDirectIndirectFlag(sWDTO.getResDirectIndirectFlag());
		serviceWorkDTO.setServiceSeqNo(sWDTO.getServiceSeqNo());
		serviceWorkDTO.setServiceWorkSeqNo(sWDTO.getServiceWorkSeqNo());
		serviceWorkDTO.setBilledflag(sWDTO.getBilledflag());
		serviceWorkDTO.setOkflag(sWDTO.getOkflag());
		serviceWorkDTO.setDoneflag(sWDTO.getDoneflag());
		serviceWorkDTO.setJobAllocStatus(sWDTO.getJobAllocStatus());
		serviceWorkDTO.setResAllocStatus(sWDTO.getResAllocStatus());
		serviceWorkDTO.setJobautoflag(sWDTO.getJobautoflag());
		serviceWorkDTO.setResautoflag(sWDTO.getResautoflag());
		serviceWorkDTO.setRemark(sWDTO.getRemark());
		return serviceWorkDTO;
	}

	private synchronized ServiceWorkMaster setServiceWorkMaster(ServiceWorkMaster_DTO sWDTO) {
		ServiceWorkMaster ServiceWorkMaster = new ServiceWorkMaster();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(sWDTO.getOnDate(), formatter);
		Timestamp ts = Timestamp.valueOf(dateTime);
		ServiceWorkMaster.setOnDate(ts);
		ServiceWorkMaster.setAutoAllocStatus(sWDTO.getAutoAllocStatus());
		ServiceWorkMaster.setCreatedBy(sWDTO.getCreatedBy());
		ServiceWorkMaster.setPartySeqNo(sWDTO.getPartySeqNo());
		ServiceWorkMaster.setBillingCurrencySeqNo(sWDTO.getBillingCurrencySeqNo());
		ServiceWorkMaster.setBookingSeqNo(sWDTO.getBookingSeqNo());
		ServiceWorkMaster.setJobAllocStatus(sWDTO.getJobAllocStatus());
		ServiceWorkMaster.setMembershipSeqNo(sWDTO.getMembershipSeqNo());
		ServiceWorkMaster.setParentServiceWorkSeqNo(sWDTO.getParentServiceWorkSeqNo());
		ServiceWorkMaster.setRequestSeqNo(sWDTO.getRequestSeqNo());
		ServiceWorkMaster.setResAllocStatus(sWDTO.getResAllocStatus());
		ServiceWorkMaster.setResDirectIndirectFlag(sWDTO.getResDirectIndirectFlag());
		ServiceWorkMaster.setServiceSeqNo(sWDTO.getServiceSeqNo());
		ServiceWorkMaster.setBilledflag(sWDTO.getBilledflag());
		ServiceWorkMaster.setOkflag(sWDTO.getOkflag());
		ServiceWorkMaster.setDoneflag(sWDTO.getDoneflag());
		ServiceWorkMaster.setJobAllocStatus(sWDTO.getJobAllocStatus());
		ServiceWorkMaster.setResAllocStatus(sWDTO.getResAllocStatus());
		ServiceWorkMaster.setJobautoflag(sWDTO.getJobautoflag());
		ServiceWorkMaster.setResautoflag(sWDTO.getResautoflag());
		ServiceWorkMaster.setRemark(sWDTO.getRemark());
		return ServiceWorkMaster;
	}

}