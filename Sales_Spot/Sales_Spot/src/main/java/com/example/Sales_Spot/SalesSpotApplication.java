package com.example.Sales_Spot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Sales_Spot.domain.Task;
import com.example.Sales_Spot.domain.TaskRepository;

@SpringBootApplication
public class SalesSpotApplication {
	private static final Logger log = LoggerFactory.getLogger(SalesSpotApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SalesSpotApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(TaskRepository repository
			) {
		return (args) -> {
		
			
			log.info("fetch all tasks");
			for (Task task : repository.findAll()) {
				log.info(task.toString());
			}
		

		
		};
	}
}
