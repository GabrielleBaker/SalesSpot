package com.example.Sales_Spot.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PriorityRepository extends CrudRepository<Priority, Long> {
	Priority findById(long id);
	
	List<Priority> findByName(@Param("priority") String name);
}
