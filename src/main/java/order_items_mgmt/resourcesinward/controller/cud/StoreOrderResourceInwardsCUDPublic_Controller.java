package order_items_mgmt.resourcesinward.controller.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import order_items_mgmt.resourcesinward.model.dto.StoreOrderResourceInward_DTO;
import order_items_mgmt.resourcesinward.services.cud.I_StoreOrderResourceInwardsCUDPublic_Service;

@RestController
@RequestMapping("/storeOrderInwardsCUDPublicMgmt")
public class StoreOrderResourceInwardsCUDPublic_Controller 
{
	@Autowired
	private I_StoreOrderResourceInwardsCUDPublic_Service storeOrderResourceInwardsCUDPublicServ;

	@PostMapping(value = "/newStoreOrderInward", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<StoreOrderResourceInward_DTO> newStoreOrderInward(@RequestBody StoreOrderResourceInward_DTO storeOrderResourceInward_DTO) 
	{
		CompletableFuture<StoreOrderResourceInward_DTO> future = null;
		StoreOrderResourceInward_DTO st = null;
		try {
			future = storeOrderResourceInwardsCUDPublicServ.newStoreOrderInward(storeOrderResourceInward_DTO);
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

	@PutMapping(value = "/updStoreOrderInward", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void updStoreOrderInward(@RequestBody StoreOrderResourceInward_DTO storeOrderResourceInward_DTO) 
	{
		storeOrderResourceInwardsCUDPublicServ.updStoreOrderInward(storeOrderResourceInward_DTO);
		return;
	}

	@DeleteMapping(value = "/delStoreOrderInward/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void delStoreOrderInward(@PathVariable Long id) 
	{
	storeOrderResourceInwardsCUDPublicServ.delStoreOrderInward(id);
	return;
	}
	
	@DeleteMapping(value = "/delSelectStoreOrdersInward", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void delStoreOrderInward(@PathVariable CopyOnWriteArrayList<Long> rnos) 
	{
	storeOrderResourceInwardsCUDPublicServ.delSelectStoreOrdersInward(rnos);
	return;
	}
	
	@PutMapping(value = "/addQtyRequested/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void addQtyRequested(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceInwardsCUDPublicServ.addQtyRequested(qty, id);
		return;
	}

	@PutMapping(value = "/subQtyRequested/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void subQtyRequested(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceInwardsCUDPublicServ.subQtyRequested(qty, id);
		return;
	}
	
	@PutMapping(value = "/setQtyRequested/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void setQtyRequested(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceInwardsCUDPublicServ.setQtyRequested(qty, id);
		return;
	}

	@PutMapping(value = "/addQtyAllocated/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void addQtyAllocated(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceInwardsCUDPublicServ.addQtyAllocated(qty, id);
		return;
	}

	@PutMapping(value = "/subQtyAllocated/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void subQtyAllocated(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceInwardsCUDPublicServ.subQtyAllocated(qty, id);
		return;
	}
	
	@PutMapping(value = "/setQtyAllocated/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void setQtyAllocated(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceInwardsCUDPublicServ.setQtyAllocated(qty, id);
		return;
	}
	
	@PutMapping(value = "/addQtyBooked/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void addQtyBooked(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceInwardsCUDPublicServ.addQtyBooked(qty, id);
		return;
	}

	@PutMapping(value = "/subQtyBooked/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void subQtyBooked(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceInwardsCUDPublicServ.subQtyBooked(qty, id);
		return;
	}
	
	@PutMapping(value = "/setQtyBooked/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void setQtyBooked(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceInwardsCUDPublicServ.setQtyBooked(qty, id);
		return;
	}

	@PutMapping(value = "/addQtyMoved/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void addQtyMoved(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceInwardsCUDPublicServ.addQtyMoved(qty, id);
		return;
	}

	@PutMapping(value = "/subQtyMoved/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void subQtyMoved(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceInwardsCUDPublicServ.subQtyMoved(qty, id);
		return;
	}
	
	@PutMapping(value = "/setQtyMoved/{qty}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void setQtyMoved(@PathVariable Float qty, @PathVariable Long id) 
	{
		storeOrderResourceInwardsCUDPublicServ.setQtyMoved(qty, id);
		return;
	}

	@PutMapping(value = "/updateBookStatus/{id}/{st}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void updateBookStatus(@PathVariable Long id, @PathVariable Character sr) 
	{
		storeOrderResourceInwardsCUDPublicServ.updateBookStatus(id, sr);
		return;
	}

	@PutMapping(value = "/clearBookStatus/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void clearBookStatus(@PathVariable Long id) 
	{
		storeOrderResourceInwardsCUDPublicServ.clearBookStatus(id);
		return;
	}
	
	@PutMapping(value = "/updateDoneFlag/{id}/{st}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void updateDoneFlag(@PathVariable Long id, @PathVariable Character sr) 
	{
		storeOrderResourceInwardsCUDPublicServ.updateDoneFlag(id, sr);
		return;
	}

	@PutMapping(value = "/clearDoneFlag/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void clearDoneFlag(@PathVariable Long id) 
	{
		storeOrderResourceInwardsCUDPublicServ.clearDoneFlag(id);
		return;
	}

	@PutMapping(value = "/updateOKFlag/{id}/{st}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void updateOKStatus(@PathVariable Long id, @PathVariable Character sr) 
	{
		storeOrderResourceInwardsCUDPublicServ.updateOKFlag(id, sr);
		return;
	}

	@PutMapping(value = "/clearOKFlag/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void clearOKFlag(@PathVariable Long id) 
	{
		storeOrderResourceInwardsCUDPublicServ.clearOKFlag(id);
		return;
	}

	
}

