package message_mgmt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MessageSender 
{
	
	private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);

	private final TopicProducer topicProducer;

	public void sendMessage(String message) 
	{
		topicProducer.send(message);
	}

	public TopicProducer getTopicProducer() {
		return topicProducer;
	}

	public MessageSender(TopicProducer topicProducer) {
		super();
		this.topicProducer = topicProducer;
	}

	

}
