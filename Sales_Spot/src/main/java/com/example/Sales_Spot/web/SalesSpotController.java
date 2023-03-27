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
	
	//order page
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
		//	model.addAttribute("customer", custRepository.findAll());
			return "viewbestel";
		}
		
		//edit order
		@RequestMapping(value = "/editbestel/{id}")
		public String editbestel(@PathVariable("id") Long bestelId, Model model) {
			model.addAttribute("bestel", bestelRepository.findById(bestelId));
			model.addAttribute("customers", custRepository.findAll());
			return "editbestel";
		}

//REST get all books
	//@RequestMapping(value = "/books", method = RequestMethod.GET)
	//public @ResponseBody List<Book> bookListRest() {
	//	return (List<Book>) repository.findAll();
//	}
//
	// RESTful service to get book by id
//	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
//	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
//		return repository.findById(bookId);
//	}

//new book
	@RequestMapping(value = "/addTask", method = RequestMethod.GET)
	public String addtasks(Model model) {
		model.addAttribute("task", new Task());
		model.addAttribute("priorities", priorityRepository.findAll());
		return "addTask";
	}

//save book
	@RequestMapping(value = "/savetask", method = RequestMethod.POST)
	public String savetask(Task task) {
		repository.save(task);
		return "redirect:home";

	}

//delete book
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//	@PreAuthorize("hasAuthority('ADMIN')")
	public String deletetask(@PathVariable("id") Long taskId, Model model) {
		repository.deleteById(taskId);
		return "redirect:../home";
	}

//edit book
	@RequestMapping(value = "/edittask/{id}")
	public String edittask(@PathVariable("id") Long taskId, Model model) {
		model.addAttribute("task", repository.findById(taskId));
		model.addAttribute("priorities", priorityRepository.findAll());
		return "edittask";
	}

}
