package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.model.Sample;

@Service
public class SampleQueueService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SampleQueueService.class);
			
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${spring.rabbitmq.exchange.name}")
	private String exchangeName;	
	@Value("${spring.rabbitmq.routing.key}")
	private String routingKey;
	
	public void sendAMessageToRabbitMq(Sample sample) {
		
		LOGGER.info("Queue service. Queued a sample is {}", sample.toString());
		
		amqpTemplate.convertAndSend(exchangeName, routingKey, sample);
		
		LOGGER.info("A message is successfully sent.");
	}


	
	@RabbitListener(queues = "${spring.rabbitmq.queue.name}")
	public void queueListener(Sample sample) {
		
		LOGGER.info("====================== Queue Listener ======================");
		LOGGER.info("Queue service. Dequeued a sample is {}", sample.toString());

	}


	
}
