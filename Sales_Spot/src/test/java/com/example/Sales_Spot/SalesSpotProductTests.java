package com.example.Sales_Spot;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.Sales_Spot.domain.Product;
import com.example.Sales_Spot.domain.ProductRepository;


//a test class for product entity
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SalesSpotApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SalesSpotProductTests {

	@Autowired
    private ProductRepository productRepo;
	
	//testing find by name and adds to a new array list
	@Test
    public void findByName() {
        List<Product> products = productRepo.findByName("Kong Chew Toy S");
        //checks size of array list and determines that the cost of the item is correct
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getPrice()).isEqualTo((long)8);
    }
	
	//check that we can find a product by Id and return its name to test for an expected result
	@Test
    public void findById() {
		//testing find by id
        Product product = productRepo.findById(1);
       
        assertThat(product.getName()).isEqualTo("Kong Chew Toy XL");
    }
	
	//test creation of new product
	@Test
    public void createProduct() {
    	Product product = new Product("Vetramil Ointment 250ml", "Propalis disinfectant ointment",(long) 10);
    	productRepo.save(product);
    	assertThat(product.getProductId()).isNotNull();
    }
	
	//test deletion of a product
	@Test
    public void deleteProduct() {
		List<Product> products = productRepo.findByName("Kong Chew Toy XL");
		Product product = products.get(0);
		productRepo.delete(product);
		List<Product> newProducts = productRepo.findByName("Kong Chew Toy XL");
		assertThat(newProducts).hasSize(0);
     }
	
	
	
}
