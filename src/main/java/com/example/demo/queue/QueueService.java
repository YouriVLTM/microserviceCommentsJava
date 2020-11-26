package com.example.demo.queue;


import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@Component
public class QueueService implements MessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueService.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String destination, String message) {
        LOGGER.info("sending message='{}' to destination='{}'", message, destination);
        jmsTemplate.convertAndSend(destination, message);
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof ActiveMQTextMessage) {
            ActiveMQTextMessage textMessage = (ActiveMQTextMessage) message;
            try {
                LOGGER.info("Processing task " + textMessage.getText());
                Thread.sleep(5000);
                LOGGER.info("Completed task " + textMessage.getText());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else {
            LOGGER.error("Message is not a text message " + message.toString());
        }
    }
}