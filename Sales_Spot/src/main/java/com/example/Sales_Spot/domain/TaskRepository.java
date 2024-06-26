package com.example.Sales_Spot.domain;
//task repository for home page task board
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface TaskRepository extends CrudRepository<Task, Long> {
	Task findById(long id);

	List<Task> findByName(@Param("name") String name);
}
