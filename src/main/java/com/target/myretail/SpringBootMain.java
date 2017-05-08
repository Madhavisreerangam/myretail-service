package com.target.myretail;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Entry point for the application
 * 
 * @author madhavi
 *
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableMongoRepositories
public class SpringBootMain {
	
	private static final Logger logger = LogManager.getLogger("SpringBootMain");

	public static void main(String args[]) {
		logger.info("From springBootMain, launching the application");
		SpringApplication.run(SpringBootMain.class, args);
	}
}
