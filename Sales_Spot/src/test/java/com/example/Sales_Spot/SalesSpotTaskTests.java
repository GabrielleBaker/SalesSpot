package com.example.Sales_Spot;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.Sales_Spot.domain.AppUser;
import com.example.Sales_Spot.domain.AppUserRepository;
import com.example.Sales_Spot.domain.Bestel;
import com.example.Sales_Spot.domain.BestelRepository;
import com.example.Sales_Spot.domain.CustomerRepository;
import com.example.Sales_Spot.domain.PriorityRepository;
import com.example.Sales_Spot.domain.Product;
import com.example.Sales_Spot.domain.ProductRepository;
import com.example.Sales_Spot.domain.StatusRepository;
import com.example.Sales_Spot.domain.Task;
import com.example.Sales_Spot.domain.TaskRepository;


//a test class for product entity
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SalesSpotApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SalesSpotTaskTests {

	
	@Autowired
	private TaskRepository Repo;
	@Autowired
	private PriorityRepository pRepo;
	//finding by name
	@Test
    public void findByName() {
        List<Task> tasks = Repo.findByName("Call Kari -Musti ja Mirri");
        //checks size of array list and determines that the cost of the item is correct
        assertThat(tasks).hasSize(1);
        assertThat(tasks.get(0).getId()).isNotNull();
    }

	//find by id testcase
	@Test
    public void findById() {
		//testing find by id
       Task task = Repo.findById(2);
        assertThat(task.getName()).isNotNull();
    }
	
	//test creation of new task
	@Test
    public void createTask() {
		Task task = new Task("Cancel Order -Musti ja Mirri", pRepo.findByName("High").get(0));
    	Repo.save(task);
    	assertThat(task.getId()).isNotNull();
    }
	
	//test deletion of task
		@Test
	    public void deleteTask() {
			List<Task> tasks = Repo.findByName("Cancel Order -Musti ja Mirri");
			Task task = tasks.get(0);
			Repo.delete(task);
			List<Task> newtasks = Repo.findByName("Cancel Order -Musti ja Mirri");
			assertThat(newtasks).hasSize(0);
	     }
	     
	
	
	
}
