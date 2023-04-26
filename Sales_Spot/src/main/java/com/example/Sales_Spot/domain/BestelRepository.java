package com.example.Sales_Spot.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//repository for orders to be found via name or id
@RepositoryRestResource
public interface BestelRepository extends CrudRepository<Bestel, Long> {
	Bestel findById(long id);
	List<Bestel> findByName(@Param("name") String name);
}
