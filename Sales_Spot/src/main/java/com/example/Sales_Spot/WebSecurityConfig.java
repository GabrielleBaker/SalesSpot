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
//@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
	@Autowired
	private UserDetailServiceImpl UserDetailService;

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
		.requestMatchers("/css/**").permitAll()
		.requestMatchers("/edittask/**", "/addtask", "/home", "/deletetask/**").hasAuthority("USER")
		.requestMatchers("/edittask/**", "/addtask", "/home", "/deletetask/**").hasAuthority("ADMIN")
		.and()
		.authorizeHttpRequests().requestMatchers("/signup", "/saveuser").permitAll()
		.and()
		.authorizeHttpRequests().anyRequest().authenticated()
		.and()
		.headers().frameOptions().disable() //for h2 console		
		.and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/home", true).permitAll()
		.and()
		.logout().permitAll();
		
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(UserDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}

//	@Bean
//	public UserDetailsService userDetailsService() {
	//	List<UserDetails> users = new ArrayList();
		//UserDetails user = User.withDefaultPasswordEncoder().username("user").password("user").roles("USER").build();

//		users.add(user);

	//	user = User.withDefaultPasswordEncoder().username("admin").password("admin").roles("USER", "ADMIN").build();

	//	users.add(user);
		// User user1 = new User("user",
		// "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
		// User user2 = new User("admin",
		// "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
		// UserRepository.save(user1);
		// urepository.save(user2);

	//	return new InMemoryUserDetailsManager(users);
//	}
}
