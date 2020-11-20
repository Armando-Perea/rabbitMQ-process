package com.java.rabbitmq.tps.subscriber;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
@Component
public class Subscriber {
 
    private static final Logger LOGGER = LoggerFactory.getLogger(Subscriber.class);
    
    public static Integer counter = 0;
    
    public static List<String> messagesList = new ArrayList<>();
 
    @Autowired
    Queue queue;
 
    @RabbitListener(queues = "#{queue.getName()}")  // Dynamically reading the queue name using SpEL from the "queue" object.
    public void receive(final String message) {

        LOGGER.info("Received the following message from the queue= " + message);
        messagesList.add(message);
        if(messagesList.size()==10) {
        	processMessages();
        }
    }
    
    public static void processMessages() {
    	
    	for(int i=0; i < messagesList.size(); i++ ) {
    		messagesList.remove(i);
    	}
    	LOGGER.info("cleared!");
    	messagesList.forEach(System.out::println);
    	LOGGER.info("New Bunch of Messages Ready to receive!");
    }
}
