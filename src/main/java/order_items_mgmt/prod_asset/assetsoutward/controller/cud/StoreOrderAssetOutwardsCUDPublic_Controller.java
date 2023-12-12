package order_items_mgmt.prod_asset.assetsoutward.controller.cud;

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

import order_items_mgmt.prod_asset.assetsoutward.model.dto.StoreOrderAssetOutward_DTO;
import order_items_mgmt.prod_asset.assetsoutward.services.cud.I_StoreOrderAssetOutwardsCUDPublic_Service;

@RestController
@RequestMapping("/storeOrderAssetOutwardsCUDPublicMgmt")
public class StoreOrderAssetOutwardsCUDPublic_Controller 
{
	@Autowired
	private I_StoreOrderAssetOutwardsCUDPublic_Service storeOrderAssetOutwardsCUDPublicServ;

	@PostMapping(value = "/newStoreOrderOutward", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<StoreOrderAssetOutward_DTO> newStoreOrderOutward(@RequestBody StoreOrderAssetOutward_DTO storeOrderAssetOutward_DTO) 
	{
		CompletableFuture<StoreOrderAssetOutward_DTO> future = null;
		StoreOrderAssetOutward_DTO st = null;
		try {
			future = storeOrderAssetOutwardsCUDPublicServ.newStoreOrderOutward(storeOrderAssetOutward_DTO);
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
	public void updStoreOrderOutward(@RequestBody StoreOrderAssetOutward_DTO storeOrderAssetOutward_DTO) 
	{
		storeOrderAssetOutwardsCUDPublicServ.updStoreOrderOutward(storeOrderAssetOutward_DTO);
		return;
	}

	@DeleteMapping(value = "/delStoreOrderOutward/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void delStoreOrderOutward(@PathVariable Long id) 
	{
	storeOrderAssetOutwardsCUDPublicServ.delStoreOrderOutward(id);
	return;
	}
	
	@DeleteMapping(value = "/delSelectStoreOrdersOutward", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void delStoreOrderOutward(@PathVariable CopyOnWriteArrayList<Long> rnos) 
	{
	storeOrderAssetOutwardsCUDPublicServ.delSelectStoreOrdersOutward(rnos);
	return;
	}
	
	@PutMapping(value = "/setFlagRequested/{flag}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void setFlagRequested(@PathVariable Character flag, @PathVariable Long id) 
	{
		storeOrderAssetOutwardsCUDPublicServ.setFlagRequested(flag, id);
		return;
	}

	@PutMapping(value = "/setFlagAllocated/{flag}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void setFlagAllocated(@PathVariable Character flag, @PathVariable Long id) 
	{
		storeOrderAssetOutwardsCUDPublicServ.setFlagAllocated(flag, id);
		return;
	}
	
	@PutMapping(value = "/setFlagBooked/{flag}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void setFlagBooked(@PathVariable Character flag, @PathVariable Long id) 
	{
		storeOrderAssetOutwardsCUDPublicServ.setFlagBooked(flag, id);
		return;
	}

	@PutMapping(value = "/setFlagMoved/{flag}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void setFlagMoved(@PathVariable Character flag, @PathVariable Long id) 
	{
		storeOrderAssetOutwardsCUDPublicServ.setFlagMoved(flag, id);
		return;
	}

	@PutMapping(value = "/updateBookStatus/{id}/{st}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void updateBookStatus(@PathVariable Long id, @PathVariable Character sr) 
	{
		storeOrderAssetOutwardsCUDPublicServ.updateBookStatus(id, sr);
		return;
	}

	@PutMapping(value = "/clearBookStatus/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void clearBookStatus(@PathVariable Long id) 
	{
		storeOrderAssetOutwardsCUDPublicServ.clearBookStatus(id);
		return;
	}
	
	@PutMapping(value = "/updateDoneFlag/{id}/{st}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void updateDoneFlag(@PathVariable Long id, @PathVariable Character sr) 
	{
		storeOrderAssetOutwardsCUDPublicServ.updateDoneFlag(id, sr);
		return;
	}

	@PutMapping(value = "/clearDoneFlag/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void clearDoneFlag(@PathVariable Long id) 
	{
		storeOrderAssetOutwardsCUDPublicServ.clearDoneFlag(id);
		return;
	}

	@PutMapping(value = "/updateOKFlag/{id}/{st}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void updateOKStatus(@PathVariable Long id, @PathVariable Character sr) 
	{
		storeOrderAssetOutwardsCUDPublicServ.updateOKFlag(id, sr);
		return;
	}

	@PutMapping(value = "/clearOKFlag/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void clearOKFlag(@PathVariable Long id) 
	{
		storeOrderAssetOutwardsCUDPublicServ.clearOKFlag(id);
		return;
	}

	
}

