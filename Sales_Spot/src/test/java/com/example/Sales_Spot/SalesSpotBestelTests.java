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
import com.example.Sales_Spot.domain.Product;
import com.example.Sales_Spot.domain.ProductRepository;
import com.example.Sales_Spot.domain.StatusRepository;


//a test class for product entity
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SalesSpotApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SalesSpotBestelTests {

	@Autowired
    private BestelRepository Repo;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StatusRepository statusRepository;
	
	//checking orders exist and have a valid product
	@Test
    public void findByName() {
        List<Bestel> bestels = Repo.findByName("Toy Restock");
        //checks size of array list and determines that the cost of the item is correct
        assertThat(bestels).hasSize(1);
        assertThat(bestels.get(0).getProduct()).isNotNull();
    }
	//checking we can find an order by name and access its product description
	@Test
    public void findByName2() {
        List<Bestel> bestels = Repo.findByName("Toy Restock");
        //checks size of array list and determines that the cost of the item is correct
        assertThat(bestels).hasSize(1);
        assertThat(bestels.get(0).getProduct().getDescription()).isEqualTo("Large plastic chew toy for dogs");
    }

	//check that we can find an by Id and return its name to test for an expected result
	@Test
    public void findById() {
		//testing find by id
        Bestel bestel = Repo.findById(2);
        assertThat(bestel.getName()).isEqualTo("Spring Selection");
    }
	
	//test creation of new order
	@Test
    public void createBestel() {
		Bestel bestel = new Bestel("Winter Selection", customerRepository.findByName("Musti ja Mirri").get(0), 
				productRepository.findByName("Sunny-Pups Strawberry Summer M").get(0), 
				(long) 500,"2023-05-07",statusRepository.findByName("Shipped").get(0)
				);
    	Repo.save(bestel);
    	assertThat(bestel.getId()).isNotNull();
    }
	
	//test deletion of order
	//cant be run at the same time as find by name and find by name 2 as this deletes the item those
	//tests are searching for
		@Test
	    public void deleteBestel() {
			List<Bestel> bs = Repo.findByName("Toy Restock");
			Bestel bestel = bs.get(0);
			Repo.delete(bestel);
			List<Bestel> Nbs = Repo.findByName("Toy Restock");
			assertThat(Nbs).hasSize(0);
	     }
	     
	
	
	
}
