package com.example.Sales_Spot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.example.Sales_Spot.domain.Status;
import com.example.Sales_Spot.domain.StatusRepository;
import com.example.Sales_Spot.domain.CustomerRepository;
import com.example.Sales_Spot.domain.Customer;

@Controller
public class SalesSpotController {

	// collection of repositories
	@Autowired
	private TaskRepository repository;
	@Autowired
	private PriorityRepository priorityRepository;
	@Autowired
	private CustomerRepository custRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private BestelRepository bestelRepository;
	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private CustomerRepository customerRepository;

	// login endpoint
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	// logout returns to login page
	@RequestMapping(value = "/logout")
	public String logout() {
		return "login";
	}

	// home page
	@RequestMapping(value = "/home")
	public String helloSecure(Model model) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		model.addAttribute("name", username);
		model.addAttribute("tasks", repository.findAll());
		return "home";
	}

	// order pages
	// bestel word used instead of order as it seemed to create errors when using
	// the word order
	// order might be a keyword similar to user, unsure but changed to avoid issues
	// bestel = to order in Afrikaans :)

	// to order list page
	@RequestMapping(value = "/bestel")
	public String bestel(Model model) {
		model.addAttribute("bestels", bestelRepository.findAll());
		return "bestel";
	}

	// view specific order summary
	@RequestMapping(value = "/viewbestel/{id}")
	public String viewbestel(@PathVariable("id") Long bestelId, Model model) {
		model.addAttribute("bestel", bestelRepository.findById(bestelId));
		return "viewbestel";
	}

	// edit specific order
	@RequestMapping(value = "/editbestel/{id}")
	public String editbestel(@PathVariable("id") Long bestelId, Model model) {
		model.addAttribute("bestel", bestelRepository.findById(bestelId));
		model.addAttribute("customers", custRepository.findAll());
		model.addAttribute("products", productRepository.findAll());
		model.addAttribute("statuses", statusRepository.findAll());
		return "editbestel";
	}

	// delete order
	// only admin may delete orders
	@RequestMapping(value = "/deletebestel/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deletebestel(@PathVariable("id") Long bestelId, Model model) {
		bestelRepository.deleteById(bestelId);
		return "redirect:../bestel";
	}

	// add new order
	@RequestMapping(value = "/newBestel", method = RequestMethod.GET)
	public String addbestel(Model model) {
		model.addAttribute("bestel", new Bestel());
		model.addAttribute("priorities", priorityRepository.findAll());
		model.addAttribute("customers", custRepository.findAll());
		model.addAttribute("products", productRepository.findAll());
		model.addAttribute("statuses", statusRepository.findAll());
		return "newBestel";
	}

	// save order
	@RequestMapping(value = "/savebestel", method = RequestMethod.POST)
	public String savebestel(Bestel bestel) {
		bestelRepository.save(bestel);
		return "redirect:bestel";

	}

	// product pages
	// Product catalog
	@RequestMapping(value = "/productList")
	public String catalog(Model model) {
		model.addAttribute("products", productRepository.findAll());
		return "productList";
	}
	
	// adding,editing,deleting products is for admin only
	//regular sales staff can only select from the list of products

	// new product
	@RequestMapping(value = "/newproduct", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addproduct(Model model) {
		model.addAttribute("product", new Product());
		return "newproduct";
	}

	// save product
	@RequestMapping(value = "/saveproduct", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveproduct(Product product) {
		productRepository.save(product);
		return "redirect:productList";
	}
	// edit product
	@RequestMapping(value = "/editproduct/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editproduct(@PathVariable("id") Long productId, Model model) {
		model.addAttribute("product", productRepository.findById(productId));
		return "editproduct";
	}
	// delete product
	@RequestMapping(value = "/deleteproduct/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteproduct(@PathVariable("id") Long productId, Model model) {
		productRepository.deleteById(productId);
		return "redirect:../productList";
	}

	// customer pages

	// Customer list
	@RequestMapping(value = "/customerlist")
	public String customers(Model model) {
		model.addAttribute("customers", customerRepository.findAll());
		return "customerlist";
	}

	// new customer
	@RequestMapping(value = "/newcustomer", method = RequestMethod.GET)
	public String addcustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "newcustomer";
	}

	// save customer
	@RequestMapping(value = "/savecustomer", method = RequestMethod.POST)
	public String savecustomer(Customer customer) {
		customerRepository.save(customer);
		return "redirect:customerlist";
	}

	// delete customer
	@RequestMapping(value = "/deletecustomer/{id}", method = RequestMethod.GET)
	// only admin may delete customers
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deletecustomer(@PathVariable("id") Long customerId, Model model) {
		customerRepository.deleteById(customerId);
		return "redirect:../customerlist";
	}

	// edit customer
	@RequestMapping(value = "/editcustomer/{id}")
	public String editcustomer(@PathVariable("id") Long customerId, Model model) {
		model.addAttribute("customer", customerRepository.findById(customerId));
		return "editcustomer";
	}

	//tasks on homepage
	// new task
	@RequestMapping(value = "/addTask", method = RequestMethod.GET)
	public String addtasks(Model model) {
		model.addAttribute("task", new Task());
		model.addAttribute("priorities", priorityRepository.findAll());
		return "addTask";
	}

	// save task
	@RequestMapping(value = "/savetask", method = RequestMethod.POST)
	public String savetask(Task task) {
		repository.save(task);
		return "redirect:home";
	}

	// delete task
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deletetask(@PathVariable("id") Long taskId, Model model) {
		repository.deleteById(taskId);
		return "redirect:../home";
	}

	// edit task
	@RequestMapping(value = "/edittask/{id}")
	public String edittask(@PathVariable("id") Long taskId, Model model) {
		model.addAttribute("task", repository.findById(taskId));
		model.addAttribute("priorities", priorityRepository.findAll());
		return "edittask";
	}

}
