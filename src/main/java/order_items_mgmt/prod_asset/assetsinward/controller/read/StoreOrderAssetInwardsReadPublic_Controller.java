package order_items_mgmt.prod_asset.assetsinward.controller.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import order_items_mgmt.prod_asset.assetsinward.model.dto.StoreOrderAssetInward_DTO;
import order_items_mgmt.prod_asset.assetsinward.services.read.I_StoreOrderAssetInwardsReadPublic_Service;

@RestController
@RequestMapping("/storeOrderAssetInwardsReadPublicMgmt")
public class StoreOrderAssetInwardsReadPublic_Controller {
	@Autowired
	private I_StoreOrderAssetInwardsReadPublic_Service storeOrderAssetInwardsReadPublicServ;

	@GetMapping(value = "/getAllStoreInwards", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<StoreOrderAssetInward_DTO>> getAllStoreInwards() {
		CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetInward_DTO>> stMaster_DTOs = null;
		CopyOnWriteArrayList<StoreOrderAssetInward_DTO> stMasterList = null;
		stMaster_DTOs = storeOrderAssetInwardsReadPublicServ.getAllStoreInwards();
		stMasterList = stMaster_DTOs.join();
		return new ResponseEntity<>(stMasterList, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllStoreInwardsForJobs", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<StoreOrderAssetInward_DTO>> getAllStoreInwardsForJobs(
			@RequestBody CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetInward_DTO>> stMaster_DTOs = null;
		CopyOnWriteArrayList<StoreOrderAssetInward_DTO> stMasterList = null;
		stMaster_DTOs = storeOrderAssetInwardsReadPublicServ.getAllStoreInwardsForJobs(ids);
		stMasterList = stMaster_DTOs.join();
		return new ResponseEntity<>(stMasterList, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllStoreInwardsForJobsDone", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<StoreOrderAssetInward_DTO>> getAllStoreInwardsForJobsDone(
			@RequestBody CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetInward_DTO>> stMaster_DTOs = null;
		CopyOnWriteArrayList<StoreOrderAssetInward_DTO> stMasterList = null;
		stMaster_DTOs = storeOrderAssetInwardsReadPublicServ.getRowsForJobWorksDone(ids);
		stMasterList = stMaster_DTOs.join();
		return new ResponseEntity<>(stMasterList, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectOrderInwards", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<StoreOrderAssetInward_DTO>> getSelectOrderInwards(
			@RequestBody CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetInward_DTO>> stMaster_DTOs = null;
		CopyOnWriteArrayList<StoreOrderAssetInward_DTO> stMasterList = null;
		stMaster_DTOs = storeOrderAssetInwardsReadPublicServ.getSelectOrderInwards(ids);
		stMasterList = stMaster_DTOs.join();
		return new ResponseEntity<>(stMasterList, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllRowsForMode/{md}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<StoreOrderAssetInward_DTO>> getAllRowsForMode(@PathVariable Integer md) {
		CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetInward_DTO>> stMaster_DTOs = null;
		CopyOnWriteArrayList<StoreOrderAssetInward_DTO> stMasterList = null;
		stMaster_DTOs = storeOrderAssetInwardsReadPublicServ.getAllRowsForMode(md);
		stMasterList = stMaster_DTOs.join();
		return new ResponseEntity<>(stMasterList, HttpStatus.OK);
	}

	@GetMapping(value = "/getIsBookedStatus/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Character> getIsBookedStatus(@PathVariable Long id) {
		CompletableFuture<Character> future = null;
		Character st = null;
		future = storeOrderAssetInwardsReadPublicServ.getIsbookedStatus(id);
		st = future.join();
		return new ResponseEntity<>(st, HttpStatus.OK);
	}

	@GetMapping(value = "/getIsDoneStatus/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Character> getIsDoneStatus(@PathVariable Long id) {
		CompletableFuture<Character> future = null;
		Character st = null;

		future = storeOrderAssetInwardsReadPublicServ.getIsDoneStatus(id);
		st = future.join();
		return new ResponseEntity<>(st, HttpStatus.OK);
	}

	@GetMapping(value = "/getIsOkStatus/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Character> getIsOkStatus(@PathVariable Long id) {
		CompletableFuture<Character> future = null;
		Character st = null;
		future = storeOrderAssetInwardsReadPublicServ.getIsOkStatus(id);
		st = future.join();
		return new ResponseEntity<>(st, HttpStatus.OK);
	}

	@GetMapping(value = "/getOrderRequestedFlag/{sid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Character> getOrderRequestedFlag(@PathVariable Long sid) {
		CompletableFuture<Character> qty = null;
		Character result = null;
		qty = storeOrderAssetInwardsReadPublicServ.getOrderRequestedFlag(sid);
		result = qty.join();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountRequestedForJob/{jid}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountRequestedForJob(@PathVariable Long jid, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		qty = storeOrderAssetInwardsReadPublicServ.getCountRequestedForJob(jid, rid, mid);
		result = qty.join();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountRequestedBeforeThisRequest/{sid}/{rid}/{mid}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountRequestedBeforeThisRequest(@PathVariable Long sid, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		qty = storeOrderAssetInwardsReadPublicServ.getCountRequestedBeforeThisRequest(sid, rid, mid);
		result = qty.join();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountRequestedBeforeDTTM/{dt}/{rid}/{mid}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountRequestedBeforeDTTM(@PathVariable String dt, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		qty = storeOrderAssetInwardsReadPublicServ.getCountRequestedBeforeDTTM(dt, rid, mid);
		result = qty.join();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getOrderAllocatedFlag/{sid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Character> getOrderAllocatedFlag(@PathVariable Long sid) {
		CompletableFuture<Character> qty = null;
		Character result = null;
		qty = storeOrderAssetInwardsReadPublicServ.getOrderAllocatedFlag(sid);
		result = qty.join();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountAllocatedForJob/{jid}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountAllocatedForJob(@PathVariable Long jid, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		qty = storeOrderAssetInwardsReadPublicServ.getCountAllocatedForJob(jid, rid, mid);
		result = qty.join();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountAllocatedBeforeThisRequest/{sid}/{rid}/{mid}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountAllocatedBeforeThisRequest(@PathVariable Long sid, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		qty = storeOrderAssetInwardsReadPublicServ.getCountAllocatedBeforeThisRequest(sid, rid, mid);
		result = qty.join();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountAllocatedBeforeDTTM/{dt}/{rid}/{mid}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountAllocatedBeforeDTTM(@PathVariable String dt, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		qty = storeOrderAssetInwardsReadPublicServ.getCountAllocatedBeforeDTTM(dt, rid, mid);
		result = qty.join();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getOrderBookedFlag/{sid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Character> getOrderBookedFlag(@PathVariable Long sid) {
		CompletableFuture<Character> qty = null;
		Character result = null;
		qty = storeOrderAssetInwardsReadPublicServ.getOrderBookedFlag(sid);
		result = qty.join();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountBookedForJob/{jid}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountBookedForJob(@PathVariable Long jid, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		qty = storeOrderAssetInwardsReadPublicServ.getCountBookedForJob(jid, rid, mid);
		result = qty.join();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountBookedBeforeThisRequest/{sid}/{rid}/{mid}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountBookedBeforeThisRequest(@PathVariable Long sid, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		qty = storeOrderAssetInwardsReadPublicServ.getCountBookedBeforeThisRequest(sid, rid, mid);
		result = qty.join();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountBookedBeforeDTTM/{dt}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountBookededBeforeDTTM(@PathVariable String dt, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		qty = storeOrderAssetInwardsReadPublicServ.getCountBookedBeforeDTTM(dt, rid, mid);
		result = qty.join();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getMovedFlagForRequest/{sid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Character> getMovedFlagForRequest(@PathVariable Long sid) {
		CompletableFuture<Character> qty = null;
		Character result = null;
		qty = storeOrderAssetInwardsReadPublicServ.getOrderMovedFlag(sid);
		result = qty.join();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountMovedForAssetForJob/{jid}/{rid}/{mid}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountMovedForAssetForJob(@PathVariable Long jid, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		qty = storeOrderAssetInwardsReadPublicServ.getCountMovedForJob(jid, rid, mid);
		result = qty.join();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountMovedForAssetBeforeThisRequest/{sid}/{rid}/{mid}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountMovedForAssetBeforeThisRequest(@PathVariable Long sid, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		qty = storeOrderAssetInwardsReadPublicServ.getCountMovedBeforeThisRequest(sid, rid, mid);
		result = qty.join();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountMovedBeforeDTTM/{dt}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountMovedBeforeDTTM(@PathVariable String dt, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		qty = storeOrderAssetInwardsReadPublicServ.getCountMovedBeforeDTTM(dt, rid, mid);
		result = qty.join();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getTotalRowCount", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountRowCount() {
		CompletableFuture<Float> qty = null;
		Float result = null;
		qty = storeOrderAssetInwardsReadPublicServ.getTotalRowCount();
		result = qty.join();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountRowsForAssetsBeforeThisDTTM/{dt}/{rid}/{mid}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountRowsForAssetsBeforeThisDTTM(@PathVariable String dt, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		qty = storeOrderAssetInwardsReadPublicServ.getTotalRowsForAssetsBeforeThisDTTM(dt, rid, mid);
		result = qty.join();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
