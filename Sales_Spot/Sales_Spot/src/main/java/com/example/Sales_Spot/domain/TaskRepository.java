package com.example.Sales_Spot.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
public interface TaskRepository extends CrudRepository<Task, Long> {
//	Task findById(long id);

	List<Task> findByName(String name);
}
