package message_mgmt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TopicProducer {
	private static final Logger logger = LoggerFactory.getLogger(TopicProducer.class);

	@Value("${topic.name.producer}")
	private String topicName;

	private final KafkaTemplate<String, String> kafkaTemplate;

	public void send(String message) {
		logger.info("Payload enviado: {}", message);
		kafkaTemplate.send(topicName, message);
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public KafkaTemplate<String, String> getKafkaTemplate() {
		return kafkaTemplate;
	}

	public TopicProducer(String topicName, KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.topicName = topicName;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	

}
