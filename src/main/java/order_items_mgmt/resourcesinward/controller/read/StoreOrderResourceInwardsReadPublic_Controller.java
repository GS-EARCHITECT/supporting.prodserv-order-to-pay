package order_items_mgmt.resourcesinward.controller.read;

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

import order_items_mgmt.resourcesinward.model.dto.StoreOrderResourceInward_DTO;
import order_items_mgmt.resourcesinward.services.read.I_StoreOrderResourceInwardsReadPublic_Service;

@RestController
@RequestMapping("/storeOrderResourceInwardsReadPublicMgmt")
public class StoreOrderResourceInwardsReadPublic_Controller 
{
	@Autowired
	private I_StoreOrderResourceInwardsReadPublic_Service storeOrderResourceInwardsReadPublicServ;

	@GetMapping(value = "/getAllStoreInwards", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<StoreOrderResourceInward_DTO>> getAllStoreInwards() 
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceInward_DTO>> stMaster_DTOs = null;
		CopyOnWriteArrayList<StoreOrderResourceInward_DTO> stMasterList = null;
		try {
			stMaster_DTOs = storeOrderResourceInwardsReadPublicServ.getAllStoreInwards();
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

	@GetMapping(value = "/getSelectOrderInwards", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<StoreOrderResourceInward_DTO>> getSelectOrderInwards(@RequestBody CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceInward_DTO>> stMaster_DTOs = null;
		CopyOnWriteArrayList<StoreOrderResourceInward_DTO> stMasterList = null;
		try {
			stMaster_DTOs = storeOrderResourceInwardsReadPublicServ.getSelectOrderInwards(ids);
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
	public ResponseEntity<CopyOnWriteArrayList<StoreOrderResourceInward_DTO>> getAllRowsForMode(@PathVariable Integer md) 
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceInward_DTO>> stMaster_DTOs = null;
		CopyOnWriteArrayList<StoreOrderResourceInward_DTO> stMasterList = null;
		try {
			stMaster_DTOs = storeOrderResourceInwardsReadPublicServ.getAllRowsForMode(md);
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
			future = storeOrderResourceInwardsReadPublicServ.getIsBookedStatus(id);
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
			future = storeOrderResourceInwardsReadPublicServ.getIsDoneStatus(id);
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
			future = storeOrderResourceInwardsReadPublicServ.getIsOkStatus(id);
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
			qty = storeOrderResourceInwardsReadPublicServ.getOrderRequestedQty(sid);
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
			qty = storeOrderResourceInwardsReadPublicServ.getTotalRequestedForJob(jid, rid, mid);
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
			qty = storeOrderResourceInwardsReadPublicServ.getTotalQtyRequestedBeforeThisRequest(sid, rid, mid);
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
			qty = storeOrderResourceInwardsReadPublicServ.getTotalQtyRequestedBeforeDTTM(dt, rid, mid);
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
			qty = storeOrderResourceInwardsReadPublicServ.getOrderAllocatedQty(sid);
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
			qty = storeOrderResourceInwardsReadPublicServ.getTotalAllocatedForJob(jid, rid, mid);
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
			qty = storeOrderResourceInwardsReadPublicServ.getTotalQtyAllocatedBeforeThisRequest(sid, rid, mid);
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
			qty = storeOrderResourceInwardsReadPublicServ.getTotalQtyAllocatedBeforeDTTM(dt, rid, mid);
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
			qty = storeOrderResourceInwardsReadPublicServ.getOrderBookedQty(sid);
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
			qty = storeOrderResourceInwardsReadPublicServ.getTotalBookedForJob(jid, rid, mid);
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
			qty = storeOrderResourceInwardsReadPublicServ.getTotalQtyBookedBeforeThisRequest(sid, rid, mid);
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
			qty = storeOrderResourceInwardsReadPublicServ.getTotalQtyBookedBeforeDTTM(dt, rid, mid);
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
			qty = storeOrderResourceInwardsReadPublicServ.getMovedQtyForRequest(sid);
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
			qty = storeOrderResourceInwardsReadPublicServ.getTotalQtyMovedForResourceForJob(jid, rid, mid);
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
			qty = storeOrderResourceInwardsReadPublicServ.getTotalQtyMovedForResourceBeforeThisRequest(sid, rid, mid);
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
			qty = storeOrderResourceInwardsReadPublicServ.getTotalQtyMovedForResourceBeforeThisRequest(sd,rid,mid);
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
			qty = storeOrderResourceInwardsReadPublicServ.getTotalRowsForResourcesBeforeThisDTTM(dt, rid, mid);
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
			qty = storeOrderResourceInwardsReadPublicServ.getTotalRowCount();
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

