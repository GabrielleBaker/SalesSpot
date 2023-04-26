package com.example.Sales_Spot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.Sales_Spot.web.UserDetailServiceImpl;


@Configuration

@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
	@Autowired
	private UserDetailServiceImpl UserDetailService;

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
		.requestMatchers(
				//home page and tasks
				"/home","/editTask/**", "/addTask",  "/deletetask/**", 
				//orders
				"/bestel", "/editbestel/**", "/newBestel","/viewbestel/**","/deletebestel/**","/savebestel",
				//customers
				"/customerlist", "/editcustomer/**","/newcustomer","/deletecustomer/**","/savecustomer",
				//products
				"/productList","/editproduct/**","/newproduct","/deleteproduct/**","/saveproduct",
				//users
				"/saveuser","/signup"
				//only those authenticated can access the pages of the site
				).authenticated()
		.and()
		.authorizeHttpRequests().anyRequest().authenticated()		
		.and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/home", true).permitAll()
		.and()
		.logout().permitAll()
		.and()
		.httpBasic();
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(UserDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
