package order_items_mgmt.prod_asset.assetsinward.controller.cud;

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

import common.model.dto.StoreOrderAssetInward_DTO;
import order_items_mgmt.prod_asset.assetsinward.services.cud.I_StoreOrderAssetInwardsCUDPublic_Service;

@RestController
@RequestMapping("/storeOrderAssetInwardsCUDPublicMgmt")
public class StoreOrderAssetInwardsCUDPublic_Controller 
{
	@Autowired
	private I_StoreOrderAssetInwardsCUDPublic_Service storeOrderAssetInwardsCUDPublicServ;

	@PostMapping(value = "/newStoreOrderInward", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<StoreOrderAssetInward_DTO> newStoreOrderInward(@RequestBody StoreOrderAssetInward_DTO storeOrderAssetInward_DTO) 
	{
		CompletableFuture<StoreOrderAssetInward_DTO> future = null;
		StoreOrderAssetInward_DTO st = null;
		try {
			future = storeOrderAssetInwardsCUDPublicServ.newStoreOrderInward(storeOrderAssetInward_DTO);
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
	public void updStoreOrderInward(@RequestBody StoreOrderAssetInward_DTO storeOrderAssetInward_DTO) 
	{
		storeOrderAssetInwardsCUDPublicServ.updStoreOrderInward(storeOrderAssetInward_DTO);
		return;
	}

	@DeleteMapping(value = "/delStoreOrderInward/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void delStoreOrderInward(@PathVariable Long id) 
	{
	storeOrderAssetInwardsCUDPublicServ.delStoreOrderInward(id);
	return;
	}
	
	@DeleteMapping(value = "/delSelectStoreOrdersInward", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void delStoreOrderInward(@PathVariable CopyOnWriteArrayList<Long> rnos) 
	{
	storeOrderAssetInwardsCUDPublicServ.delSelectStoreOrdersInward(rnos);
	return;
	}
	
	@PutMapping(value = "/setFlagRequested/{flag}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void setFlagRequested(@PathVariable Character flag, @PathVariable Long id) 
	{
		storeOrderAssetInwardsCUDPublicServ.setFlagRequested(flag, id);
		return;
	}

	@PutMapping(value = "/setFlagAllocated/{flag}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void setFlagAllocated(@PathVariable Character flag, @PathVariable Long id) 
	{
		storeOrderAssetInwardsCUDPublicServ.setFlagAllocated(flag, id);
		return;
	}
	
	@PutMapping(value = "/setFlagBooked/{flag}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void setFlagBooked(@PathVariable Character flag, @PathVariable Long id) 
	{
		storeOrderAssetInwardsCUDPublicServ.setFlagBooked(flag, id);
		return;
	}

	@PutMapping(value = "/setFlagMoved/{flag}/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void setFlagMoved(@PathVariable Character flag, @PathVariable Long id) 
	{
		storeOrderAssetInwardsCUDPublicServ.setFlagMoved(flag, id);
		return;
	}

	@PutMapping(value = "/updateBookStatus/{id}/{st}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void updateBookStatus(@PathVariable Long id, @PathVariable Character sr) 
	{
		storeOrderAssetInwardsCUDPublicServ.updateBookStatus(id, sr);
		return;
	}

	@PutMapping(value = "/clearBookStatus/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void clearBookStatus(@PathVariable Long id) 
	{
		storeOrderAssetInwardsCUDPublicServ.clearBookStatus(id);
		return;
	}
	
	@PutMapping(value = "/updateDoneFlag/{id}/{st}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void updateDoneFlag(@PathVariable Long id, @PathVariable Character sr) 
	{
		storeOrderAssetInwardsCUDPublicServ.updateDoneFlag(id, sr);
		return;
	}

	@PutMapping(value = "/clearDoneFlag/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void clearDoneFlag(@PathVariable Long id) 
	{
		storeOrderAssetInwardsCUDPublicServ.clearDoneFlag(id);
		return;
	}

	@PutMapping(value = "/updateOKFlag/{id}/{st}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void updateOKStatus(@PathVariable Long id, @PathVariable Character sr) 
	{
		storeOrderAssetInwardsCUDPublicServ.updateOKFlag(id, sr);
		return;
	}

	@PutMapping(value = "/clearOKFlag/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void clearOKFlag(@PathVariable Long id) 
	{
		storeOrderAssetInwardsCUDPublicServ.clearOKFlag(id);
		return;
	}

	
}

