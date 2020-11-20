package com.java.rabbitmq.tps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.java.rabbitmq.tps.controller.Publisher;
 
// @SpringBootApplication annotation enables the auto-configuration feature of the spring boot module (i.e. java-based configuration and component scanning).
@SpringBootApplication
@EnableRabbit
@EnableScheduling
public class SpringbootRabbitmq {
 
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootRabbitmq.class);
    
    @Autowired
    Publisher publisher;
     
    public static void main(String[] args) {
        // The "run()" method returns the "ConfigurableApplicationContext" instance which can be further used by the spring application.
        SpringApplication.run(SpringbootRabbitmq.class, args);
        LOGGER.info("Springboot application with rabbitmq started successfully.");
    }
    
 // REMEMBER THAT Connection test must be done at least every minute to keep connection!
 	@Scheduled(cron = "0 0/1 * * * *")
 	public void testConnection() {
 		for(int i= 0; i<=12; i++)
 		publisher.send("Hello "+i);
 	}
    
}
