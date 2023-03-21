package com.example.Sales_Spot.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.Sales_Spot.domain.TaskRepository;

@Controller
public class SalesSpotController {

	@Autowired
	private TaskRepository taskRepository;
	


	// ensuring login details
	@RequestMapping(value = "/saleshome")
	public String helloSecure(Model model) {
		model.addAttribute("tasks", taskRepository.findAll());
		return "salespersonHome";
	}

}
