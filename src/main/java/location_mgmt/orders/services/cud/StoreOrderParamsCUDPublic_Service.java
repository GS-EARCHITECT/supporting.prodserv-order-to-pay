package location_mgmt.orders.services.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import common.model.dto.*;
import common.model.master.*;
import location_mgmt.orders.model.repo.cud.StoreOrderParamsCUDPublic_Repo;

@Service("storeOrderParamsCUDPublicServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class StoreOrderParamsCUDPublic_Service implements I_StoreOrderParamsCUDPublic_Service {

	// private static final Logger logger =
	// LoggerFactory.getLogger(StoreOrderParamsDataService.class);

	@Autowired
	private StoreOrderParamsCUDPublic_Repo storeOrderParamsCUDPublicRepo;

	@Autowired
	private Executor asyncExecutor;

	@Override
	public CompletableFuture<StoreOrderParamsData_DTO> newStoreOrderParam(
			StoreOrderParamsData_DTO storeOrderParamsData_DTO) {
		CompletableFuture<StoreOrderParamsData_DTO> future = CompletableFuture.supplyAsync(() -> {
			StoreOrderParamsData_DTO stoDTO = null;
			StoreOrderParamsDataPK storeOrderParamsDataPK = new StoreOrderParamsDataPK();
			storeOrderParamsDataPK.setStoreRquestSeqNo(storeOrderParamsData_DTO.getStoreRequestSeqNo());

			if (!storeOrderParamsCUDPublicRepo.existsById(storeOrderParamsDataPK)) {
				stoDTO = this.getStoreOrderParamsData_DTO(
						storeOrderParamsCUDPublicRepo.save(this.setStoreOrderParamsData(storeOrderParamsData_DTO)));
			}
			return stoDTO;
		}, asyncExecutor);

		return future;

	}

	@Override
	public CompletableFuture<Void> updStoreOrderParam(StoreOrderParamsData_DTO storeOrderParamsData_DTO) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			StoreOrderParamsData sto = null;
			StoreOrderParamsDataPK storeOrderParamsDataPK = new StoreOrderParamsDataPK();
			storeOrderParamsDataPK.setStoreRquestSeqNo(storeOrderParamsData_DTO.getStoreRequestSeqNo());

			if (storeOrderParamsCUDPublicRepo.existsById(storeOrderParamsDataPK)) {
				this.getStoreOrderParamsData_DTO(
						storeOrderParamsCUDPublicRepo.save(this.setStoreOrderParamsData(storeOrderParamsData_DTO)));
			}
			return;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<Void> updStoreOrderParamRequest(String rParam, Long storeReqSeqNo) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			StoreOrderParamsDataPK storeOrderParamsDataPK = new StoreOrderParamsDataPK();
			storeOrderParamsDataPK.setStoreRquestSeqNo(storeReqSeqNo);

			if (storeOrderParamsCUDPublicRepo.existsById(storeOrderParamsDataPK)) {
				storeOrderParamsCUDPublicRepo.updStoreOrderParamRequest(rParam, storeReqSeqNo);
			}
			return;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<Void> updStoreOrderParamLocation(String lParam, Long storeReqSeqNo) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			StoreOrderParamsDataPK storeOrderParamsDataPK = new StoreOrderParamsDataPK();
			storeOrderParamsDataPK.setStoreRquestSeqNo(storeReqSeqNo);

			if (storeOrderParamsCUDPublicRepo.existsById(storeOrderParamsDataPK)) {
				storeOrderParamsCUDPublicRepo.updStoreOrderParamLocation(lParam, storeReqSeqNo);
			}
			return;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<Void> delSelectStoreOrderParams(
			CopyOnWriteArrayList<StoreOrderParamsDataPK> storeOrderParamsDataPKs) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			storeOrderParamsCUDPublicRepo.deleteAllById(storeOrderParamsDataPKs);
			return;
		}, asyncExecutor);

		return future;

	}

	@Override
	public CompletableFuture<Void> delAllStoreOrderParams() {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			storeOrderParamsCUDPublicRepo.deleteAll();
			return;
		}, asyncExecutor);

		return future;

	}

	private synchronized StoreOrderParamsData_DTO getStoreOrderParamsData_DTO(
			StoreOrderParamsData storeOrderParamsData) {
		StoreOrderParamsData_DTO storeOrderParamsData_DTO = new StoreOrderParamsData_DTO();
		storeOrderParamsData_DTO.setLocationParams(storeOrderParamsData.getLocationParams());
		storeOrderParamsData_DTO.setRequestParams(storeOrderParamsData.getRequestParams());
		storeOrderParamsData_DTO
				.setStoreRequestSeqNo(storeOrderParamsData.getStoreOrderParamsDataPK().getStoreRquestSeqNo());
		return storeOrderParamsData_DTO;
	}

	private synchronized StoreOrderParamsData setStoreOrderParamsData(
			StoreOrderParamsData_DTO storeOrderParamsData_DTO) {
		StoreOrderParamsData storeOrderParamsData = new StoreOrderParamsData();
		StoreOrderParamsDataPK storeOrderParamsDataPK = new StoreOrderParamsDataPK();
		storeOrderParamsData.setLocationParams(storeOrderParamsData_DTO.getLocationParams());
		storeOrderParamsData.setRequestParams(storeOrderParamsData_DTO.getRequestParams());
		storeOrderParamsDataPK.setStoreRquestSeqNo(storeOrderParamsData_DTO.getStoreRequestSeqNo());
		storeOrderParamsData.setStoreOrderParamsDataPK(storeOrderParamsDataPK);
		return storeOrderParamsData;
	}

}