package com.example.Sales_Spot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Sales_Spot.domain.CustomerRepository;
import com.example.Sales_Spot.domain.BestelRepository;
import com.example.Sales_Spot.domain.PriorityRepository;
import com.example.Sales_Spot.domain.Task;
import com.example.Sales_Spot.domain.TaskRepository;
import com.example.Sales_Spot.domain.Bestel;
import com.example.Sales_Spot.domain.Product;
import com.example.Sales_Spot.domain.ProductRepository;


@Controller
public class SalesSpotController {

	@Autowired
	private TaskRepository repository;
	@Autowired
	private PriorityRepository priorityRepository;
	@Autowired
	private CustomerRepository custRepository;
	@Autowired
	private BestelRepository bestelRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/logout")
	public String logout() {
		return "login";
	}

	//home page
	@RequestMapping(value = "/home")
	public String helloSecure(Model model) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		// System.out.println("USERNAME: " + username);
		model.addAttribute("name", username);
		model.addAttribute("tasks", repository.findAll());
		return "home";
	}
	
	//order pages, bestel word used instead of order as it seemed to create errors when using the word order
	//order might be a keyword, bestel = to order in Afrikaans
		@RequestMapping(value = "/bestel")
		public String bestel(Model model) {
			UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = user.getUsername();
			// System.out.println("USERNAME: " + username);
			model.addAttribute("name", username);
			model.addAttribute("bestels", bestelRepository.findAll());
			return "bestel";
		}
		
		//view order
		@RequestMapping(value = "/viewbestel/{id}")
		public String viewbestel(@PathVariable("id") Long bestelId, Model model) {
			model.addAttribute("bestel", bestelRepository.findById(bestelId));
			return "viewbestel";
		}
		
		//edit order
		@RequestMapping(value = "/editbestel/{id}")
		public String editbestel(@PathVariable("id") Long bestelId, Model model) {
			model.addAttribute("bestel", bestelRepository.findById(bestelId));
			model.addAttribute("customers", custRepository.findAll());
			return "editbestel";
		}
		
		//add new order
		@RequestMapping(value = "/newBestel", method = RequestMethod.GET)
		public String addbestel(Model model) {
			model.addAttribute("bestel", new Bestel());
			model.addAttribute("priorities", priorityRepository.findAll());
			model.addAttribute("customers", custRepository.findAll());
			return "newBestel";
		}
		//save order
		@RequestMapping(value = "/savebestel", method = RequestMethod.POST)
		public String savebestel(Bestel bestel) {
			bestelRepository.save(bestel);
			return "redirect:bestel";

		}
		
	//Product catalog
		@RequestMapping(value = "/productList")
		public String catalog(Model model) {
			model.addAttribute("products", productRepository.findAll());
			return "productList";
		}
		
//tasks on homepage

//new task
	@RequestMapping(value = "/addTask", method = RequestMethod.GET)
	public String addtasks(Model model) {
		model.addAttribute("task", new Task());
		model.addAttribute("priorities", priorityRepository.findAll());
		return "addTask";
	}

//save task
	@RequestMapping(value = "/savetask", method = RequestMethod.POST)
	public String savetask(Task task) {
		repository.save(task);
		return "redirect:home";
	}

//delete task
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//	@PreAuthorize("hasAuthority('ADMIN')")
	public String deletetask(@PathVariable("id") Long taskId, Model model) {
		repository.deleteById(taskId);
		return "redirect:../home";
	}

//edit task
	@RequestMapping(value = "/edittask/{id}")
	public String edittask(@PathVariable("id") Long taskId, Model model) {
		model.addAttribute("task", repository.findById(taskId));
		model.addAttribute("priorities", priorityRepository.findAll());
		return "edittask";
	}

}
