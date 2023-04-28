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
import com.example.Sales_Spot.domain.Task;
import com.example.Sales_Spot.domain.TaskRepository;
import com.example.Sales_Spot.domain.Product;
import com.example.Sales_Spot.domain.ProductRepository;
import com.example.Sales_Spot.domain.Status;
import com.example.Sales_Spot.domain.StatusRepository;
@SpringBootApplication
public class SalesSpotApplication {
	private static final Logger log = LoggerFactory.getLogger(SalesSpotApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SalesSpotApplication.class, args);
	}
/*
	@Bean
	public CommandLineRunner demo(TaskRepository repository, PriorityRepository priorityRepository,
			AppUserRepository urepository, CustomerRepository customerRepository, 
			BestelRepository bestelRepository, ProductRepository productRepository, StatusRepository statusRepository) {
		return (args) -> {
			// some priority levels
			log.info("save a couple of tasks");
			priorityRepository.save(new Priority("High"));
			priorityRepository.save(new Priority("Medium"));
			priorityRepository.save(new Priority("Low"));

			// some tasks
			Task task1 = new Task("Call Kari -Musti ja Mirri", priorityRepository.findByName("High").get(0));
			Task task2 = new Task("Check delivery status- Petenkoiratarvike", priorityRepository.findByName("Low").get(0));
			repository.save(task1);
			repository.save(task2);

			// some customers
			log.info("save a couple of customers");
			customerRepository.save(new Customer("Musti ja Mirri", "274 Koirakatu Helsinki 0223", "22 Lintu Polku Vantaa 0225",
					"0803053055", "Kari Kesa"));
			customerRepository.save(new Customer("Petenkoiratarvike", "12B Kissapolku Oulu 0550", "144 Fredrikinkatu Helsinki 5505",
					"0488844047", "Sanna Marin"));
			customerRepository.save(new Customer("Omaelainklinikka", "4C Piispansilta Pasila 5508", "2 Tiistilantie Espoo 8788",
					"0854920758", "Teija Vanikainen"));
			
			//some products
			log.info("save a couple of products");
			productRepository.save(new Product("Kong Chew Toy XL", "Large plastic chew toy for dogs",(long) 12));
			productRepository.save(new Product("Kong Chew Toy S", "Small plastic chew toy for dogs",(long) 8));
			productRepository.save(new Product("Lavendar Lovelies", "Lavendar scented poop bags for dogs x50",(long) 14));
			productRepository.save(new Product("Sunny-Pups Strawberry Summer M", "Gel cooling pad for dogs",(long) 20));
			productRepository.save(new Product("Vetramil Spray 100ml", "Propalis disinfectant spray",(long) 10));
			
			//some statuses
			log.info("some statuses");
			statusRepository.save(new Status("Order opened"));
			statusRepository.save(new Status("Pending payment"));
			statusRepository.save(new Status("Shipped"));
			statusRepository.save(new Status("Delivered"));
			statusRepository.save(new Status("Cancelled"));
			
			// some orders
			Bestel bestel1 = new Bestel("Toy Restock", customerRepository.findByName("Petenkoiratarvike").get(0), 
					productRepository.findByName("Kong Chew Toy XL").get(0), 
					(long) 100,"2023-04-27",statusRepository.findByName("Delivered").get(0)
					);
			Bestel bestel2 = new Bestel("Spring Selection", customerRepository.findByName("Musti ja Mirri").get(0), 
					productRepository.findByName("Sunny-Pups Strawberry Summer M").get(0), 
					(long) 50,"2023-05-09",statusRepository.findByName("Shipped").get(0)
					);

			bestelRepository.save(bestel1);
			bestelRepository.save(bestel2);

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
	}*/
}
