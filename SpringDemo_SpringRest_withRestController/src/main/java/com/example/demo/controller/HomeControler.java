package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.CustomerRep;
import com.example.demo.model.Customer;

@RestController
public class HomeControler {
	@Autowired
	CustomerRep rep; // here CustomerRep is an interface we are not creating class and injecting the Object.

	@RequestMapping("/")
	public ModelAndView home() {
		System.out.println("----Inside home() ----");

		ModelAndView mv = new ModelAndView();
		mv.setViewName("home"); // view name
		return mv; // view

	}

	@PostMapping(path = "/customer", consumes = { "application/xml" }) // here we can use what is the format server will accept application/jso or xml
	public Customer addCustomer(@RequestBody Customer cust) {

		rep.save(cust);

		return cust;
	}
	
	@PutMapping(path = "/customer", consumes = { "application/json" }) // here we can use what is the format server will accept application/jso or xml
	public Customer updateCustomer(@RequestBody Customer cust) {

		rep.save(cust);

		return cust;
	}

	@DeleteMapping("/customer/{custId}")
	public String deleteCustomer(@PathVariable("custId") int custId) {
		Customer cust = rep.getById(custId);
		rep.delete(cust);
		return "successfully Deleted";
	}

	@GetMapping("/customer") // for restriction only xml data (path="/customers"  ,produces={"application/xml"}
	@ResponseBody // here I am informing disPatcher Servlet that I am not returning any view m returning Data	
	public List<Customer> getCustomers() {
		System.out.println("inside getCustomer ");
		//return rep.findAll();
		return rep.findByCustIdGreaterThan(102);

	}

	@RequestMapping("/customer/{custId}") // Json Response {"custId":102,"custName":"patna2","custAddress":"akash2"}
    @ResponseBody // here I am informing disPatcher Servlet that I am returning any view m returning Data
	public Optional<Customer> getCustomer(@PathVariable("custId") int custId) {
		System.out.println("inside getCustomer ");
		return rep.findById(custId);

	}

}
