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
import com.example.Sales_Spot.domain.Customer;
import com.example.Sales_Spot.domain.CustomerRepository;
import com.example.Sales_Spot.domain.Priority;
import com.example.Sales_Spot.domain.PriorityRepository;
import com.example.Sales_Spot.domain.Product;
import com.example.Sales_Spot.domain.ProductRepository;
import com.example.Sales_Spot.domain.Status;
import com.example.Sales_Spot.domain.StatusRepository;




//a test class for product entity
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SalesSpotApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SalesSpotPriorityStatusTests {

	@Autowired
	private PriorityRepository Repo;
	@Autowired
	private StatusRepository sRepo;
	
	//checking  finding by name and checking id is not null
	@Test
    public void findByNamePriority() {
        List<Priority> priorities = Repo.findByName("High");
        assertThat(priorities).hasSize(1);
        assertThat(priorities.get(0).getPriorityId()).isNotNull();
    }
	//finding status by name
	@Test
    public void findByNameStatus() {
        List<Status> stats = sRepo.findByName("Shipped");
        assertThat(stats).hasSize(1);
        assertThat(stats.get(0).getStatusId()).isNotNull();
    }

	//check that we can find an by Id and return its name to test for an expected result
	@Test
    public void findByIdPriority() {
		//testing find by id
        Priority priority = Repo.findById(1);
        assertThat(priority.getName()).isEqualTo("High");
    }
	@Test
    public void findByIdStatus() {
		//testing find by id
        Status stat = sRepo.findById(1);
        assertThat(stat.getName()).isEqualTo("Order opened");
    }
	
	//test creation of new priority
	@Test
    public void createPriority() {
		Priority priority = new Priority("Urgent");
    	Repo.save(priority);
    	assertThat(priority.getPriorityId()).isNotNull();
    }
	//test creation of new status
	@Test
    public void createStatus() {
		Status stat = new Status("Refunded");
    	sRepo.save(stat);
    	assertThat(stat.getStatusId()).isNotNull();
    }
	
	//test deletion of newly made priority
		@Test
	    public void deletePriority() {
			List<Priority> priorities = Repo.findByName("Urgent");
			Priority priority = priorities.get(0);
			Repo.delete(priority);
			List<Priority> newpriorities = Repo.findByName("Urgent");
			assertThat(newpriorities).hasSize(0);
	     }
		//test deletion of status
		@Test
	    public void deleteStatus() {
			List<Status> stats = sRepo.findByName("Delivered");
			Status status = stats.get(0);
			sRepo.delete(status);
			List<Status> newstats = sRepo.findByName("Delivered");
			assertThat(newstats).hasSize(0);
	     }
	     
	
	
	
}
