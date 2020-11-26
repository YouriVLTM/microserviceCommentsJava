package com.example.demo;

import com.example.demo.queue.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;

@SpringBootApplication
public class CommentApplication implements JmsListenerConfigurer {

	@Value("${queue.name}")
	private String queueName;

	@Autowired
	private QueueService queueService;


	public static void main(String[] args) {
		SpringApplication.run(CommentApplication.class, args);
	}

	@Override
	public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
		SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
		endpoint.setId("myId");
		endpoint.setDestination(queueName);
		endpoint.setMessageListener(queueService);
		registrar.registerEndpoint(endpoint);
	}

}
