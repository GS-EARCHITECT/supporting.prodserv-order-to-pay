package request_mgmt.services.batch;

import java.util.concurrent.CompletableFuture;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import request_mgmt.model.master.ServiceRequestMaster_SDTO;

@Service("clientRequestBatchServ")
public class ClientRequestBatchListener_Service 
{
	private static final Logger logger = LoggerFactory.getLogger(ClientRequestBatchListener_Service.class);

	@Autowired
	private IClientBatchRequest_Service cRequestBatchServ;
	
	@KafkaListener(topics = "${topic.name.clientRequest}", groupId = "group_id", concurrency = "5")
	public void consume(ConsumerRecord<String, ServiceRequestMaster_SDTO> payload) 
	{
	cRequestBatchServ.getCRequestSDTO(payload.value());		
	}

}