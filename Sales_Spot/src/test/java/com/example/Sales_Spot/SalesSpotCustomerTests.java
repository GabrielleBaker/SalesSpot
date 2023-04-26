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
import com.example.Sales_Spot.domain.Product;
import com.example.Sales_Spot.domain.ProductRepository;
import com.example.Sales_Spot.domain.StatusRepository;


//a test class for product entity
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SalesSpotApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SalesSpotCustomerTests {

	@Autowired
	private CustomerRepository Repo;
	
	
	//checking  finding by name and getting contact person
	@Test
    public void findByName() {
        List<Customer> custs = Repo.findByName("Musti ja Mirri");
        assertThat(custs).hasSize(1);
        assertThat(custs.get(0).getContactPerson()).isNotNull();
    }
	

	//check that we can find an by Id and return its name to test for an expected result
	@Test
    public void findById() {
		//testing find by id
        Customer cust = Repo.findById(2);
        assertThat(cust.getName()).isEqualTo("Petenkoiratarvike");
    }
	
	//test creation of new customer
	@Test
    public void createCust() {
		Customer cust = new Customer("VetSmart", "274 Koirakatu Helsinki 0223", "22 Lintu Polku Vantaa 0225",
				"0803053055", "Kari Kesa");
    	Repo.save(cust);
    	assertThat(cust.getCustomerId()).isNotNull();
    }
	
	//test deletion 
	//cant be run at the same time as find by name  as this deletes the item those
	//tests are searching for
		@Test
	    public void deleteCust() {
			List<Customer> custs = Repo.findByName("VetSmart");
			Customer cust = custs.get(0);
			Repo.delete(cust);
			List<Customer> newcusts = Repo.findByName("VetSmart");
			assertThat(newcusts).hasSize(0);
	     }
	     
	
	
	
}
