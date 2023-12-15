package order_items_mgmt.prod_asset.resourcesoutward.controller.read;

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
import common.model.dto.StoreOrderResourceOutward_DTO;
import order_items_mgmt.prod_asset.resourcesoutward.services.read.I_StoreOrderResourceOutwardsReadPublic_Service;

@RestController
@RequestMapping("/storeOrderResourceOutwardsReadPublicMgmt")
public class StoreOrderResourceOutwardsReadPublic_Controller 
{
	@Autowired
	private I_StoreOrderResourceOutwardsReadPublic_Service storeOrderResourceOutwardsReadPublicServ;

	@GetMapping(value = "/getAllStoreOutwards", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<StoreOrderResourceOutward_DTO>> getAllStoreOutwards() 
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceOutward_DTO>> stMaster_DTOs = null;
		CopyOnWriteArrayList<StoreOrderResourceOutward_DTO> stMasterList = null;
		try {
			stMaster_DTOs = storeOrderResourceOutwardsReadPublicServ.getAllStoreOutwards();
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
	public ResponseEntity<CopyOnWriteArrayList<StoreOrderResourceOutward_DTO>> getSelectOrderOutwards(@RequestBody CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceOutward_DTO>> stMaster_DTOs = null;
		CopyOnWriteArrayList<StoreOrderResourceOutward_DTO> stMasterList = null;
		try {
			stMaster_DTOs = storeOrderResourceOutwardsReadPublicServ.getSelectOrderOutwards(ids);
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
	public ResponseEntity<CopyOnWriteArrayList<StoreOrderResourceOutward_DTO>> getAllRowsForMode(@PathVariable Integer md) 
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceOutward_DTO>> stMaster_DTOs = null;
		CopyOnWriteArrayList<StoreOrderResourceOutward_DTO> stMasterList = null;
		try {
			stMaster_DTOs = storeOrderResourceOutwardsReadPublicServ.getAllRowsForMode(md);
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
	public ResponseEntity<Character> getIsBookedStatus(@PathVariable Long id) 
	{
		CompletableFuture<Character> future = null;
		Character st = null;
		try {
			future = storeOrderResourceOutwardsReadPublicServ.getIsBookedStatus(id);
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
	public ResponseEntity<Character> getIsDoneStatus(@PathVariable Long id) 
	{
		CompletableFuture<Character> future = null;
		Character st = null;
		try {
			future = storeOrderResourceOutwardsReadPublicServ.getIsDoneStatus(id);
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
	public ResponseEntity<Character> getIsOkStatus(@PathVariable Long id) 
	{
		CompletableFuture<Character> future = null;
		Character st = null;
		try {
			future = storeOrderResourceOutwardsReadPublicServ.getIsOkStatus(id);
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
	
	@GetMapping(value = "/getOrderRequestedQty/{sid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getOrderRequestedQty(@PathVariable Long sid)
	{
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderResourceOutwardsReadPublicServ.getOrderRequestedQty(sid);
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
	
	@GetMapping(value = "/getTotalRequestedForJob/{jid}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getTotalRequestedForJob(@PathVariable Long jid, @PathVariable Long rid, @PathVariable Integer mid)
	{
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderResourceOutwardsReadPublicServ.getTotalRequestedForJob(jid, rid, mid);
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
	
	@GetMapping(value = "/getTotalQtyRequestedBeforeThisRequest/{sid}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getTotalQtyRequestedBeforeThisRequest(@PathVariable Long sid, @PathVariable Long rid, @PathVariable Integer mid)
	{
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderResourceOutwardsReadPublicServ.getTotalQtyRequestedBeforeThisRequest(sid, rid, mid);
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
	
	@GetMapping(value = "/getTotalQtyRequestedBeforeDTTM/{dt}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getTotalQtyRequestedBeforeDTTM(@PathVariable String dt, @PathVariable Long rid, @PathVariable Integer mid)
	{
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderResourceOutwardsReadPublicServ.getTotalQtyRequestedBeforeDTTM(dt, rid, mid);
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
	
	@GetMapping(value = "/getOrderAllocatedQty/{sid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getOrderAllocatedQty(@PathVariable Long sid)
	{
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderResourceOutwardsReadPublicServ.getOrderAllocatedQty(sid);
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
	
	@GetMapping(value = "/getTotalAllocatedForJob/{jid}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getTotalAllocatedForJob(@PathVariable Long jid, @PathVariable Long rid, @PathVariable Integer mid)
	{
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderResourceOutwardsReadPublicServ.getTotalAllocatedForJob(jid, rid, mid);
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
	
	@GetMapping(value = "/getTotalQtyAllocatedBeforeThisRequest/{sid}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getTotalQtyAllocatedBeforeThisRequest(@PathVariable Long sid, @PathVariable Long rid, @PathVariable Integer mid)
	{
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderResourceOutwardsReadPublicServ.getTotalQtyAllocatedBeforeThisRequest(sid, rid, mid);
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
	
	@GetMapping(value = "/getTotalQtyAllocatedBeforeDTTM/{dt}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getTotalQtyAllocatedBeforeDTTM(@PathVariable String dt, @PathVariable Long rid, @PathVariable Integer mid)
	{
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderResourceOutwardsReadPublicServ.getTotalQtyAllocatedBeforeDTTM(dt, rid, mid);
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

	@GetMapping(value = "/getOrderBookedQty/{sid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getOrderBookedQty(@PathVariable Long sid)
	{
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderResourceOutwardsReadPublicServ.getOrderBookedQty(sid);
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
	
	@GetMapping(value = "/getTotalBookedForJob/{jid}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getTotalBookedForJob(@PathVariable Long jid, @PathVariable Long rid, @PathVariable Integer mid)
	{
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderResourceOutwardsReadPublicServ.getTotalBookedForJob(jid, rid, mid);
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
	
	@GetMapping(value = "/getTotalQtyBookedBeforeThisRequest/{sid}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getTotalQtyBookedBeforeThisRequest(@PathVariable Long sid, @PathVariable Long rid, @PathVariable Integer mid)
	{
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderResourceOutwardsReadPublicServ.getTotalQtyBookedBeforeThisRequest(sid, rid, mid);
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
	
	@GetMapping(value = "/getTotalQtyBookedBeforeDTTM/{dt}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getTotalQtyBookededBeforeDTTM(@PathVariable String dt, @PathVariable Long rid, @PathVariable Integer mid)
	{
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderResourceOutwardsReadPublicServ.getTotalQtyBookedBeforeDTTM(dt, rid, mid);
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


	@GetMapping(value = "/getMovedQtyForRequest/{sid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getMovedQtyForRequest(@PathVariable Long sid)
	{
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderResourceOutwardsReadPublicServ.getMovedQtyForRequest(sid);
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
	
	@GetMapping(value = "/getTotalQtyMovedForResourceForJob/{jid}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getTotalQtyMovedForResourceForJob(@PathVariable Long jid, @PathVariable Long rid, @PathVariable Integer mid)
	{
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderResourceOutwardsReadPublicServ.getTotalQtyMovedForResourceForJob(jid, rid, mid);
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
	
	@GetMapping(value = "/getTotalQtyMovedForResourceBeforeThisRequest/{sid}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getTotalQtyMovedForResourceBeforeThisRequest(@PathVariable Long sid, @PathVariable Long rid, @PathVariable Integer mid)
	{
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderResourceOutwardsReadPublicServ.getTotalQtyMovedForResourceBeforeThisRequest(sid, rid, mid);
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
	
	@GetMapping(value = "/getTotalRowsMovedForResourcesBeforeThisDTTM/{dt}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getTotalRowsMovedForResourcesBeforeThisDTTM(@PathVariable Long sd, @PathVariable Long rid, @PathVariable Integer mid)
	{
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderResourceOutwardsReadPublicServ.getTotalQtyMovedForResourceBeforeThisRequest(sd,rid,mid);
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

	@GetMapping(value = "/getTotalRowsForResourcesBeforeThisDTTM/{dt}/{rid}/{mid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getTotalRowsForResourcesBeforeThisDTTM(@PathVariable String dt, @PathVariable Long rid, @PathVariable Integer mid)
	{
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderResourceOutwardsReadPublicServ.getTotalRowsForResourcesBeforeThisDTTM(dt, rid, mid);
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
	public ResponseEntity<Float> getTotalRowCount()
	{
		CompletableFuture<Float> qty = null;
		Float result = null;
		try {
			qty = storeOrderResourceOutwardsReadPublicServ.getTotalRowCount();
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

