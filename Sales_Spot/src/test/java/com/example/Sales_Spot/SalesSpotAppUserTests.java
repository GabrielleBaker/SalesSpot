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
import com.example.Sales_Spot.domain.Product;
import com.example.Sales_Spot.domain.ProductRepository;


//a test class for product entity
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SalesSpotApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SalesSpotAppUserTests {

	@Autowired
    private AppUserRepository Repo;
	
	//checking admin exists
	@Test
    public void findByName() {
        List<AppUser> users = Repo.findByRole("ADMIN");
        //checks size of array list and determines that the cost of the item is correct
        assertThat(users).hasSize(1);
        assertThat(users.get(0).getUsername()).isEqualTo("admin");
    }
	
	//check that we can find a product by Id and return its name to test for an expected result
	@Test
    public void findById() {
		//testing find by id
        AppUser user = Repo.findById(1);
       
        assertThat(user.getUsername()).isEqualTo("user");
    }
	
	//test creation of new user
	@Test
    public void createProduct() {
    	AppUser user = new AppUser("gabrielle", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
				"gabi@gmail.com", "USER");
    	Repo.save(user);
    	assertThat(user.getId()).isNotNull();
    }
	
	//test deletion of admin 
	//must be run separately from the first test or causes failure in first test since it deletes admin
		@Test
	    public void deleteProduct() {
			AppUser user = Repo.findByUsername("admin");
			Repo.delete(user);
			AppUser newU = Repo.findByUsername("admin");
			assertThat(newU).isNull();
	     }
	
	
	
}
