package com.example.Sales_Spot.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//product repository for finding/creating/editing/deleting products by id or name
@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product,Long> {
	Product findById(long id);
	List<Product> findByName(@Param("product") String name);
}
