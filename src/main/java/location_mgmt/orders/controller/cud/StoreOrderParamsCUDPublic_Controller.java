package location_mgmt.orders.controller.cud;

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
import common.model.dto.StoreOrderParamsData_DTO;
import common.model.master.StoreOrderParamsDataPK;
import location_mgmt.orders.services.cud.I_StoreOrderParamsCUDPublic_Service;

@RestController
@RequestMapping("/storeOrderParamsCUDPublicMgmt")
public class StoreOrderParamsCUDPublic_Controller {
	@Autowired
	private I_StoreOrderParamsCUDPublic_Service storeOrderParamsCUDPublicServ;

	@PostMapping(value = "/newStoreOrderParam", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<StoreOrderParamsData_DTO> newStoreOrderParam(
			@RequestBody StoreOrderParamsData_DTO storeOrderParamsData_DTO) {
		CompletableFuture<StoreOrderParamsData_DTO> future = null;
		StoreOrderParamsData_DTO st = null;
		try {
			future = storeOrderParamsCUDPublicServ.newStoreOrderParam(storeOrderParamsData_DTO);
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

	@PutMapping(value = "/updStoreOrderParam", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void updStoreOrderParam(@RequestBody StoreOrderParamsData_DTO storeOrderParamsData_DTO) {
		storeOrderParamsCUDPublicServ.updStoreOrderParam(storeOrderParamsData_DTO);
		return;
	}

	@PutMapping(value = "/updStoreOrderParamRequest/{reqStr}/{stSeqNo}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public void updStoreOrderParamRequest(@PathVariable String reqStr, @PathVariable Long stSeqNo) {
		storeOrderParamsCUDPublicServ.updStoreOrderParamRequest(reqStr, stSeqNo);
		return;
	}

	@PutMapping(value = "/updStoreOrderParamLocation/{locStr}/{stSeqNo}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public void updStoreOrderParamLocation(@PathVariable String locStr, @PathVariable Long stSeqNo) {
		storeOrderParamsCUDPublicServ.updStoreOrderParamLocation(locStr, stSeqNo);
		return;
	}

	@DeleteMapping(value = "/delSelectStoreOrderParams", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void delSelectStoreOrderParams(@PathVariable CopyOnWriteArrayList<StoreOrderParamsDataPK> snos) {
		storeOrderParamsCUDPublicServ.delSelectStoreOrderParams(snos);
		return;
	}

	@DeleteMapping(value = "/delAllStoreOrderParams", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void delAllSelectStoreOrderParams() {
		storeOrderParamsCUDPublicServ.delAllStoreOrderParams();
		return;
	}

}
