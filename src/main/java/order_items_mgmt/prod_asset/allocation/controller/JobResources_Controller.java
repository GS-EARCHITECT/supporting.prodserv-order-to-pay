package order_items_mgmt.prod_asset.allocation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import order_items_mgmt.prod_asset.allocation.services.online.I_ResourceAllocMgmt_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/jobResources")
public class JobResources_Controller {
	// private static final Logger logger =
	// LoggerFactory.getLogger(JobResources_Controller.class);

	@Autowired
	private I_ResourceAllocMgmt_Service resourceAllocMgmtServOnLine;

	@GetMapping(value = "/{servWorkSeqNo}")
	public void processJobResourceDetails(@PathVariable Long servWorkSeqNo) {
		resourceAllocMgmtServOnLine.resource_OnlineAlloc(servWorkSeqNo);
		return;
	}

}
