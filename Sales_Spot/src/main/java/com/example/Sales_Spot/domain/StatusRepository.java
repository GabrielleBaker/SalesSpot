package com.example.Sales_Spot.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StatusRepository extends CrudRepository<Status, Long>{
Status findById(long id);
	
	List<Status> findByName(@Param("status") String name);
}
