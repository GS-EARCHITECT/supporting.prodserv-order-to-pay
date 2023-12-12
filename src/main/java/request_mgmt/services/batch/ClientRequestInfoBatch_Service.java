package request_mgmt.services.batch;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import request_mgmt.model.master.ServiceRequestMaster;
import request_mgmt.model.master.ServiceRequestMaster_SDTO;
import request_mgmt.model.repo.read.ServiceRequestMasterRead_Repo;

@Service("clientRequestInfoBatchServ")
public class ClientRequestInfoBatch_Service implements IClientRequestInfoBatch_Service
{
	private static final Logger logger = LoggerFactory.getLogger(ClientRequestInfoBatch_Service.class);

	@Autowired
	private ServiceRequestMasterRead_Repo serviceRequestReadRepo;
	
	@Autowired
	private ClientBatchRequest_Service cRequestBatchServ;

	@Value("${topic.name.response}")
	private String topicmyResponse;

	@Autowired
	private KafkaTemplate<String, ServiceRequestMaster_SDTO> kafkaTemplateRequest;

	@Scheduled(fixedRate = 3000)
	public void runBatch() {
		// For Each Asset - Get All Res Prod Servs
		CopyOnWriteArrayList<ServiceRequestMaster> serviceRequestMasters = serviceRequestReadRepo.getSelectRequestsNotDone();

		ServiceRequestMaster serviceRequestMaster = null;

		if (serviceRequestMasters != null && serviceRequestMasters.size() > 0) 
		{
			logger.info("running for size :"+serviceRequestMasters.size());
			for (int i = 0; i < serviceRequestMasters.size(); i++) {
				serviceRequestMaster = serviceRequestMasters.get(i);
				sendToServiceManager(serviceRequestMaster);
			}
		}
		return;
	}

	public synchronized void sendToServiceManager(ServiceRequestMaster serviceRequestMaster)
	{
		CompletableFuture<ServiceRequestMaster_SDTO> future = cRequestBatchServ.getCRequestSDTOFromMaster(serviceRequestMaster);;
		ServiceRequestMaster_SDTO serviceRequestMaster_SDTO = future.join();
		
		ListenableFuture<SendResult<String, ServiceRequestMaster_SDTO>> future2 = kafkaTemplateRequest.send(topicmyResponse,serviceRequestMaster_SDTO);
		future2.addCallback(new ListenableFutureCallback<SendResult<String, ServiceRequestMaster_SDTO>>() {

			@Override
			public void onSuccess(final SendResult<String, ServiceRequestMaster_SDTO> message) 
			{
				logger.info("request no :" + message.getProducerRecord().value().getRequestSeqNo());			
			}

			@Override
			public void onFailure(final Throwable throwable) {
				logger.error("unable to send message= ", throwable);
			}
		});
		return;
	}
}
