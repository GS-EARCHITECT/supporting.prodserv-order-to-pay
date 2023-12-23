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
import org.springframework.web.reactive.function.client.WebClient;
import common.model.dto.*;
import common.model.master.*;
import location_mgmt.orders.model.repo.cud.StoreOrderParamsCUDPublic_Repo;
import location_mgmt.orders.model.repo.read.StoreOrderParamsReadPublic_Repo;
import location_mgmt.rules_mgmt.model.repo.pub.JobAssetResLocationRulesPublic_Repo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service("storeOrderParamsCUDPublicServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class StoreOrderParamsCUDPublic_Service implements I_StoreOrderParamsCUDPublic_Service {

	// private static final Logger logger =
	// LoggerFactory.getLogger(StoreOrderParamsDataService.class);

	@Autowired
	private StoreOrderParamsCUDPublic_Repo storeOrderParamsCUDPublicRepo;

	@Autowired
	private StoreOrderParamsReadPublic_Repo storeOrderParamsReadPublicRepoOnLine;

	@Autowired
	private JobAssetResLocationRulesPublic_Repo jobAssetResLocationRulesPublicRepo;

	@Autowired
	private WebClient webClient;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<Void> setSelectJobAssetResLocationParam(Long storeRquestSeqNo, Long jid, Long rid,
			Long lat, Long lon) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			JobAssetResLocationRule jobRes = jobAssetResLocationRulesPublicRepo
					.getSelectJobAssetResLocationRulesByJobResource(jid, rid);
			Double ruleDist = (double) 0;
			String locParam = "";
			StoreOrderParamsData_DTO storeOrderParamsData_DTO = null;
			StoreOrderParamsDataPK storeOrderParamsDataPK = null;
			Flux<ResourceLocationMaster_DTO> specs = null;
			CopyOnWriteArrayList<ResourceLocationMaster_DTO> specLst = new CopyOnWriteArrayList<ResourceLocationMaster_DTO>();
			Mono<LocationVector_DTO> locDTO = null;
			Mono<Integer> specDTO = null;
			LocationVector_DTO locDTO2 = new LocationVector_DTO();
			Double locLat = (double) 0;
			Double locLon = (double) 0;
			Long ruleLoc = (long) 0;
			Integer j = 0;
			CopyOnWriteArrayList<ResourceLocationMaster_DTO> filtLst = new CopyOnWriteArrayList<ResourceLocationMaster_DTO>();

			if (jobRes != null) {
				ruleDist = jobRes.getLessthanDistance();
				specs = webClient.get().uri(
						"/resourceLocationPublicReadManagement/getLocationsByResource/" + jobRes.getResourceSeqNo())
						.retrieve().bodyToFlux(ResourceLocationMaster_DTO.class);
				specs.collectList().subscribe(specLst::addAll);

				for (int i = 0; i < specLst.size(); i++) {
					locDTO = webClient.get()
							.uri("/locationVectorReadManagement/getLocationVectorsDetailsById/" + ruleLoc).retrieve()
							.bodyToMono(LocationVector_DTO.class);
					locDTO2 = locDTO.block();
					locLat = locDTO2.getLat();
					locLon = locDTO2.getLon();
					specDTO = webClient.get()
							.uri("/api/checkDistancePlain/{" + locLat + "}/{" + locLon + "}/{" + lat + "}/{" + lon
									+ "}/{" + 0 + "}/{" + 0 + "}/{" + ruleDist + "}")
							.retrieve().bodyToMono(Integer.class);
					j = specDTO.block();

					if (j > 0) {
						filtLst.add(specLst.get(i));
					}
				}

				if (filtLst != null && filtLst.size() > 0) {
					for (int i = 0; i < filtLst.size(); i++) {
						if (i == 0) {
							locParam = locParam + filtLst.get(i).toString();
						} else {
							locParam = locParam.trim() + "," + filtLst.get(i);
						}
					}
				}
				storeOrderParamsDataPK = new StoreOrderParamsDataPK();
				storeOrderParamsDataPK.setStoreRquestSeqNo(storeRquestSeqNo);

				if (storeOrderParamsReadPublicRepoOnLine.findById(storeOrderParamsDataPK).get() != null) {
					storeOrderParamsCUDPublicRepo.updStoreOrderParamLocation(locParam, storeRquestSeqNo);
				} else {
					storeOrderParamsData_DTO = new StoreOrderParamsData_DTO();
					storeOrderParamsData_DTO.setLocationParams(locParam);
					storeOrderParamsData_DTO.setStoreRequestSeqNo(storeRquestSeqNo);
					storeOrderParamsCUDPublicRepo.save(setStoreOrderParamsData(storeOrderParamsData_DTO));
				}
			}
			return;
		}, asyncExecutor);

		return future;
	}

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