package com.company.Testing;

import java.awt.print.Pageable;
import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Level;
import java.util.logging.Logger; 
@RestController
@RequestMapping("/get/customer")
public class Controller {

    private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
	
	@Autowired
	private CustomerRepository repo;
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<?>DeleteCustomer(@PathVariable("id") Integer id, Customer customer){
		try {
		Customer c1=repo.findById(id).get();
		repo.delete(c1);
		return  ResponseEntity.status(200).body("Deleted Suceesfully CustomerId "+id);
		
	}catch(Exception e) {
		return ResponseEntity.status(404).body("Doesn't exist CustomerId "+id);
	}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?>UpdateCustomer(@PathVariable("id") Integer id,@RequestBody Customer customer){
		try {
		customer.setId(id);
		repo.save(customer);
		LOGGER.info("Sucessfully Update Customer Details: "+id);
//		return ResponseEntity.ok(customer);
		return ResponseEntity.status(200).body("Sucessfully Update Customer Details: "+id);
	}catch(Exception e) {
		LOGGER.warning("Invalid datas");
		return ResponseEntity.status(500).body("Invalid Datas are trying to update");
		
	}
	}
	@PostMapping
	public ResponseEntity<?> AddCustomer(@RequestBody Customer customer) {
		try {
		repo.save(customer);
	
		LOGGER.info("Sucessfully Created Customer Details: ");
//		return  ResponseEntity.ok(customer);
		return ResponseEntity.status(200).body("Sucessfully Created Customer Details: ");
		}catch(Exception e) {
			LOGGER.warning("Invalid datas");
			return ResponseEntity.status(500).body("Invalid Datas are trying to add,please ensure the datas ");
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getcustomerById(@PathVariable Integer id) {
		
		try {
			Customer c1=repo.findById(id).get();
			LOGGER.info("Fetching Datas ------------."+id);
			return ResponseEntity.ok(c1);
			
		} catch (Exception e) {
			LOGGER.warning("Invalid Data for customerId "+id);
			return  ResponseEntity.status(404).body("No Datas Presented in given CustomerId "+id);
			
	}
	}
	@GetMapping("/hello")
	ResponseEntity<String> hello() {
	    return new ResponseEntity<>("Hello World!", HttpStatus.OK);
	}
	//Pagination ?_page=1
	@GetMapping
	public Iterable<Customer> getCustomers(
		@RequestParam(name="_page",defaultValue="1")Integer pageNum,
		@RequestParam(name="_limit",defaultValue="40")Integer pagesize) {
		PageRequest p=PageRequest.of(pageNum-1,pagesize);
		return repo.findAll(p).getContent();
		
	}
//	
//	@GetMapping
//	public List<Customer> getCustomers() {
//		
//		Customer c1= new Customer();
//		c1.setId(1);
//		c1.setName("Bless");
//		c1.setEmail("bless@gmail.com");
//		c1.setPhone("234566");
//		c1.setCity("Mavelikara");
//		c1.setState("Kerala");
//		c1.setCountry("India");
//		c1.setGender("Female");
//		Customer c2= new Customer();
//		c2.setId(2);
//		c2.setName("James");
//		c2.setEmail("James@gmail.com");
//		c2.setPhone("234590886");
//		c2.setCity("Erankulam");
//		c2.setState("Kerala");
//		c2.setCountry("India");
//		c2.setGender("Male");
//		return Arrays.asList(c1,c2);
//		
//		}

	
}
