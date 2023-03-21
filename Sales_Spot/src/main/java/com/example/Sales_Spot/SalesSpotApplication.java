package com.example.Sales_Spot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Sales_Spot.domain.AppUser;
import com.example.Sales_Spot.domain.AppUserRepository;
import com.example.Sales_Spot.domain.Customer;
import com.example.Sales_Spot.domain.CustomerRepository;
import com.example.Sales_Spot.domain.Bestel;
import com.example.Sales_Spot.domain.BestelRepository;
import com.example.Sales_Spot.domain.Priority;
import com.example.Sales_Spot.domain.PriorityRepository;
import com.example.Sales_Spot.domain.Status;
import com.example.Sales_Spot.domain.StatusRepository;
import com.example.Sales_Spot.domain.Task;
import com.example.Sales_Spot.domain.TaskRepository;

@SpringBootApplication
public class SalesSpotApplication {
	private static final Logger log = LoggerFactory.getLogger(SalesSpotApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SalesSpotApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(TaskRepository repository, PriorityRepository priorityRepository,
			AppUserRepository urepository, CustomerRepository customerRepository, BestelRepository bestelRepository,
			StatusRepository statusRepository) {
		return (args) -> {
			// some priority levels
			log.info("save a couple of tasks");
			priorityRepository.save(new Priority("High"));
			priorityRepository.save(new Priority("Medium"));
			priorityRepository.save(new Priority("Low"));

			// some tasks
			Task task1 = new Task("Call Bob", priorityRepository.findByName("High").get(0));
			Task task2 = new Task("Faxes", priorityRepository.findByName("Low").get(0));
			repository.save(task1);
			repository.save(task2);

			// some customers
			log.info("save a couple of customers");
			customerRepository.save(new Customer("Musti ja Mirri", "274 Kat Kattu Helsinki", "22 Polkupolku Helsinki",
					"+358 46 8800 407", "Joe"));
			
			//status
			statusRepository.save(new Status("Shipped"));
			
			// order
			Bestel bestel1 = new Bestel("O1", customerRepository.findByName("Musti ja Mirri").get(0), "2021",
					statusRepository.findByName("Shipped").get(0));

			bestelRepository.save(bestel1);

			// Create users: admin/admin user/user
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
					"user@gmail.com", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					"admin@gmail.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			log.info("fetch all tasks");
			for (Task task : repository.findAll()) {
				log.info(task.toString());
			}
			for (Bestel bestel : bestelRepository.findAll()) {
				log.info(bestel.toString());
			}

		};
	}
}
