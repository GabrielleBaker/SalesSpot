package com.example.Sales_Spot.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
	AppUser findById(long id);
	AppUser findByUsername(String username);
//	List<AppUser> findByUsername(@Param("username") String username);
	List<AppUser> findByRole(@Param("role") String role);
}
