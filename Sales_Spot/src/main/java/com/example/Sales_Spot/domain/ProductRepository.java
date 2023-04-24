package com.example.Sales_Spot.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product,Long> {
	Product findById(long id);
	List<Product> findByName(@Param("product") String name);
}
