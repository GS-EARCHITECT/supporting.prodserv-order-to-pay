package order_items_mgmt.resourcesoutward.controller.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import order_items_mgmt.resourcesoutward.model.dto.StoreOrderResourceOutward_DTO;
import order_items_mgmt.resourcesoutward.services.cud.I_StoreOrderResourceOutwardsCUDPublic_Service;

@RestController
@RequestMapping("/storeOrderOutwardsCUDPublicMgmt")
public class StoreOrderResourceOutwardsCUDPublic_Controller 
{
	@Autowired
	private I_StoreOrderResourceOutwardsCUDPublic_Service storeOrderResourceOutwardsCUDPublicServ;

	@PostMapping(value = "/newStoreOrderOutward", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<StoreOrderResourceOutward_DTO> newStoreOrderOutward(@RequestBody StoreOrderResourceOutward_DTO storeOrderResourceOutward_DTO) 
	{
		CompletableFuture<StoreOrderResourceOutward_DTO> future = null;
		StoreOrderResourceOutward_DTO st = null;
		try {
			future = storeOrderResourceOutwardsCUDPublicServ.newStoreOrderOutward(storeOrderResourceOutward_DTO);
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

	@PutMapping(value = "/updStoreOrderOutward", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void updStoreOrderOutward(@RequestBody StoreOrderResourceOutward_DTO storeOrderResourceOutward_DTO) 
	{
		storeOrderResourceOutwardsCUDPublicServ.updStoreOrderOutward(storeOrderResourceOutward_DTO);
		return;
	}

	@DeleteMapping(value = "/delStoreOrderOutward/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void delStoreOrderOutward(@PathVariable Long id) 
	{
	storeOrderResourceOutwardsCUDPublicServ.delStoreOrderOutward(id);
	return;
	}
	
	@DeleteMapping(value = "/delSelectStoreOrdersOutward", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void delStoreOrderOutward(@PathVariable CopyOnWriteArrayList<Long> rnos) 
	{
	storeOrderResourceOutwardsCUDPublicServ.delSelectStoreOrdersOutward(rnos);
	return;
	}
	
	@PutMapping(value = "/addQtyRequested/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void addQtyRequested(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceOutwardsCUDPublicServ.addQtyRequested(qty, id);
		return;
	}

	@PutMapping(value = "/subQtyRequested/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void subQtyRequested(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceOutwardsCUDPublicServ.subQtyRequested(qty, id);
		return;
	}
	
	@PutMapping(value = "/setQtyRequested/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void setQtyRequested(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceOutwardsCUDPublicServ.setQtyRequested(qty, id);
		return;
	}

	@PutMapping(value = "/addQtyAllocated/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void addQtyAllocated(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceOutwardsCUDPublicServ.addQtyAllocated(qty, id);
		return;
	}

	@PutMapping(value = "/subQtyAllocated/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void subQtyAllocated(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceOutwardsCUDPublicServ.subQtyAllocated(qty, id);
		return;
	}
	
	@PutMapping(value = "/setQtyAllocated/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void setQtyAllocated(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceOutwardsCUDPublicServ.setQtyAllocated(qty, id);
		return;
	}
	
	@PutMapping(value = "/addQtyBooked/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void addQtyBooked(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceOutwardsCUDPublicServ.addQtyBooked(qty, id);
		return;
	}

	@PutMapping(value = "/subQtyBooked/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void subQtyBooked(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceOutwardsCUDPublicServ.subQtyBooked(qty, id);
		return;
	}
	
	@PutMapping(value = "/setQtyBooked/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void setQtyBooked(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceOutwardsCUDPublicServ.setQtyBooked(qty, id);
		return;
	}

	@PutMapping(value = "/addQtyMoved/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void addQtyMoved(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceOutwardsCUDPublicServ.addQtyMoved(qty, id);
		return;
	}

	@PutMapping(value = "/subQtyMoved/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void subQtyMoved(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceOutwardsCUDPublicServ.subQtyMoved(qty, id);
		return;
	}
	
	@PutMapping(value = "/setQtyMoved/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void setQtyMoved(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceOutwardsCUDPublicServ.setQtyMoved(qty, id);
		return;
	}

	@PutMapping(value = "/updateBookStatus/{id}/{st}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void updateBookStatus(@PathVariable Long id, @PathVariable Character sr) 
	{
		storeOrderResourceOutwardsCUDPublicServ.updateBookStatus(id, sr);
		return;
	}

	@PutMapping(value = "/clearBookStatus/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void clearBookStatus(@PathVariable Long id) 
	{
		storeOrderResourceOutwardsCUDPublicServ.clearBookStatus(id);
		return;
	}
	
	@PutMapping(value = "/updateDoneFlag/{id}/{st}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void updateDoneFlag(@PathVariable Long id, @PathVariable Character sr) 
	{
		storeOrderResourceOutwardsCUDPublicServ.updateDoneFlag(id, sr);
		return;
	}

	@PutMapping(value = "/clearDoneFlag/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void clearDoneFlag(@PathVariable Long id) 
	{
		storeOrderResourceOutwardsCUDPublicServ.clearDoneFlag(id);
		return;
	}

	@PutMapping(value = "/updateOKFlag/{id}/{st}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void updateOKStatus(@PathVariable Long id, @PathVariable Character sr) 
	{
		storeOrderResourceOutwardsCUDPublicServ.updateOKFlag(id, sr);
		return;
	}

	@PutMapping(value = "/clearOKFlag/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void clearOKFlag(@PathVariable Long id) 
	{
		storeOrderResourceOutwardsCUDPublicServ.clearOKFlag(id);
		return;
	}

	
}

