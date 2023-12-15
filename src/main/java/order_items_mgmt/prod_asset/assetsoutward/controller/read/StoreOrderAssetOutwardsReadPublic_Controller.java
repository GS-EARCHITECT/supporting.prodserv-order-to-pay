package order_items_mgmt.prod_asset.assetsoutward.controller.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import common.model.dto.StoreOrderAssetOutward_DTO;
import order_items_mgmt.prod_asset.assetsoutward.services.read.I_StoreOrderAssetOutwardsReadPublic_Service;

@RestController
@RequestMapping("/storeOrderAssetOutwardsReadPublicMgmt")
public class StoreOrderAssetOutwardsReadPublic_Controller 
{
	@Autowired
	private I_StoreOrderAssetOutwardsReadPublic_Service storeOrderAssetOutwardsReadPublicServ;

	@GetMapping(value = "/getAllStoreOutwards", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<StoreOrderAssetOutward_DTO>> getAllStoreOutwards() {
		CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetOutward_DTO>> stMaster_DTOs = null;
		CopyOnWriteArrayList<StoreOrderAssetOutward_DTO> stMasterList = null;
		try {
			stMaster_DTOs = storeOrderAssetOutwardsReadPublicServ.getAllStoreOutwards();
			stMasterList = stMaster_DTOs.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(stMasterList, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectOrderOutwards", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<StoreOrderAssetOutward_DTO>> getSelectOrderOutwards(
			@RequestBody CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetOutward_DTO>> stMaster_DTOs = null;
		CopyOnWriteArrayList<StoreOrderAssetOutward_DTO> stMasterList = null;
		try {
			stMaster_DTOs = storeOrderAssetOutwardsReadPublicServ.getSelectOrderOutwards(ids);
			stMasterList = stMaster_DTOs.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(stMasterList, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllRowsForMode/{md}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<StoreOrderAssetOutward_DTO>> getAllRowsForMode(@PathVariable Integer md) {
		CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetOutward_DTO>> stMaster_DTOs = null;
		CopyOnWriteArrayList<StoreOrderAssetOutward_DTO> stMasterList = null;
		try {
			stMaster_DTOs = storeOrderAssetOutwardsReadPublicServ.getAllRowsForMode(md);
			stMasterList = stMaster_DTOs.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(stMasterList, HttpStatus.OK);
	}

	@GetMapping(value = "/getIsBookedStatus/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Character> getIsBookedStatus(@PathVariable Long id) {
		CompletableFuture<Character> future = null;
		Character st = null;
		try {
			future = storeOrderAssetOutwardsReadPublicServ.getIsbookedStatus(id);
			st = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(st, HttpStatus.OK);
	}

	@GetMapping(value = "/getIsDoneStatus/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Character> getIsDoneStatus(@PathVariable Long id) {
		CompletableFuture<Character> future = null;
		Character st = null;
		try {
			future = storeOrderAssetOutwardsReadPublicServ.getIsDoneStatus(id);
			st = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(st, HttpStatus.OK);
	}

	@GetMapping(value = "/getIsOkStatus/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Character> getIsOkStatus(@PathVariable Long id) {
		CompletableFuture<Character> future = null;
		Character st = null;
		try {
			future = storeOrderAssetOutwardsReadPublicServ.getIsOkStatus(id);
			st = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(st, HttpStatus.OK);
	}

	@GetMapping(value = "/getOrderRequestedFlag/{sid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Character> getOrderRequestedFlag(@PathVariable Long sid) {
		CompletableFuture<Character> qty = null;
		Character result = null;
		try {
			qty = storeOrderAssetOutwardsReadPublicServ.getOrderRequestedFlag(sid);
			result = qty.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountRequestedForJob/{jid}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountRequestedForJob(@PathVariable Long jid, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderAssetOutwardsReadPublicServ.getCountRequestedForJob(jid, rid, mid);
			result = qty.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountRequestedBeforeThisRequest/{sid}/{rid}/{mid}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountRequestedBeforeThisRequest(@PathVariable Long sid, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderAssetOutwardsReadPublicServ.getCountRequestedBeforeThisRequest(sid, rid, mid);
			result = qty.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountRequestedBeforeDTTM/{dt}/{rid}/{mid}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountRequestedBeforeDTTM(@PathVariable String dt, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderAssetOutwardsReadPublicServ.getCountRequestedBeforeDTTM(dt, rid, mid);
			result = qty.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getOrderAllocatedFlag/{sid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Character> getOrderAllocatedFlag(@PathVariable Long sid) {
		CompletableFuture<Character> qty = null;
		Character result = null;
		try {
			qty = storeOrderAssetOutwardsReadPublicServ.getOrderAllocatedFlag(sid);
			result = qty.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountAllocatedForJob/{jid}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountAllocatedForJob(@PathVariable Long jid, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderAssetOutwardsReadPublicServ.getCountAllocatedForJob(jid, rid, mid);
			result = qty.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountAllocatedBeforeThisRequest/{sid}/{rid}/{mid}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountAllocatedBeforeThisRequest(@PathVariable Long sid, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderAssetOutwardsReadPublicServ.getCountAllocatedBeforeThisRequest(sid, rid, mid);
			result = qty.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountAllocatedBeforeDTTM/{dt}/{rid}/{mid}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountAllocatedBeforeDTTM(@PathVariable String dt, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderAssetOutwardsReadPublicServ.getCountAllocatedBeforeDTTM(dt, rid, mid);
			result = qty.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getOrderBookedFlag/{sid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Character> getOrderBookedFlag(@PathVariable Long sid) {
		CompletableFuture<Character> qty = null;
		Character result = null;
		try {
			qty = storeOrderAssetOutwardsReadPublicServ.getOrderBookedFlag(sid);
			result = qty.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountBookedForJob/{jid}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountBookedForJob(@PathVariable Long jid, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderAssetOutwardsReadPublicServ.getCountBookedForJob(jid, rid, mid);
			result = qty.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountBookedBeforeThisRequest/{sid}/{rid}/{mid}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountBookedBeforeThisRequest(@PathVariable Long sid, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderAssetOutwardsReadPublicServ.getCountBookedBeforeThisRequest(sid, rid, mid);
			result = qty.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountBookedBeforeDTTM/{dt}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountBookededBeforeDTTM(@PathVariable String dt, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderAssetOutwardsReadPublicServ.getCountBookedBeforeDTTM(dt, rid, mid);
			result = qty.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getMovedFlagForRequest/{sid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Character> getMovedFlagForRequest(@PathVariable Long sid) {
		CompletableFuture<Character> qty = null;
		Character result = null;
		try {
			qty = storeOrderAssetOutwardsReadPublicServ.getOrderMovedFlag(sid);
			result = qty.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountMovedForAssetForJob/{jid}/{rid}/{mid}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountMovedForAssetForJob(@PathVariable Long jid, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderAssetOutwardsReadPublicServ.getCountMovedForJob(jid, rid, mid);
			result = qty.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountMovedForAssetBeforeThisRequest/{sid}/{rid}/{mid}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountMovedForAssetBeforeThisRequest(@PathVariable Long sid, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderAssetOutwardsReadPublicServ.getCountMovedBeforeThisRequest(sid, rid, mid);
			result = qty.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountMovedBeforeDTTM/{dt}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountMovedBeforeDTTM(@PathVariable String dt, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderAssetOutwardsReadPublicServ.getCountMovedBeforeDTTM(dt, rid, mid);
			result = qty.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getTotalRowCount", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountRowCount() {
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderAssetOutwardsReadPublicServ.getTotalRowCount();
			result = qty.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getCountRowsForAssetsBeforeThisDTTM/{dt}/{rid}/{mid}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getCountRowsForAssetsBeforeThisDTTM(@PathVariable String dt, @PathVariable Long rid,
			@PathVariable Integer mid) {
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderAssetOutwardsReadPublicServ.getTotalRowsForAssetsBeforeThisDTTM(dt, rid, mid);
			result = qty.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
