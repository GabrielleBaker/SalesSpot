package com.example.Sales_Spot.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//customer repository to find customers by name or id for deletion/editing/adding new
@RepositoryRestResource
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	Customer findById(long id);

	List<Customer> findByName(@Param("customer") String name);
}
