package com.example.demo.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.CustomerRep;
import com.example.demo.model.Customer;

@Controller
public class HomeControler {
	@Autowired
	CustomerRep rep; //  here CustomerRep is an interface we are not creating class and 
   
	@RequestMapping("/")
	public ModelAndView home() {
    System.out.println("----Inside home() ----");
	
	ModelAndView mv =new ModelAndView();
	mv.setViewName("home"); // view name
	return mv; //view
		
}
    @RequestMapping("/addCustomer")
    public ModelAndView addCustomer(Customer cust) {
    	
    	rep.save(cust);
    	ModelAndView mv =new ModelAndView();
    	mv.setViewName("home");
    	return mv;
    }
    
	
	  @RequestMapping("/getCustomer")
	  public ModelAndView getCustomer(@RequestParam  int custId) {
		  System.out.println("inside getCustomer ");
		  Customer cust=rep.findById(custId).orElse(new Customer());
		  //System.out.println(rep.findByCustIdGreaterThan(101)); 
		  ModelAndView mv =new	ModelAndView(); 
		  mv.addObject("cust", cust);
	      mv.setViewName("showCustomerData"); return mv; }
	 
    
    @RequestMapping("/customers") // for restriction only xml data (path="/customers" ,produces={"application/xml"}
    @ResponseBody               //  here I am informing disPatcher Servlet that I am returning any view m returning Data
    public List<Customer> getCustomers() {
    	System.out.println("inside getCustomer ");
    	return rep.findAll();
    	
    	
    }
    
   // string Format  Optional[Customer [custId=102, custName=patna2, custAddress=akash2]]
    
	/*
	 * @RequestMapping("/customers/{custId}")
	 * 
	 * @ResponseBody // here I am informing disPatcher Servlet that I am returning
	 * any view m returning Data public String getCustomer1(@PathVariable("custId")
	 * int custId) { System.out.println("inside getCustomer "); return
	 * rep.findById(custId).toString();
	 * 
	 * 
	 * }
	 */
	
	  @RequestMapping("/customers/{custId}") //   Json Response   {"custId":102,"custName":"patna2","custAddress":"akash2"}
	  
	  @ResponseBody // here I am informing disPatcher Servlet that I am returning any view m returning Data 
	  public Optional<Customer> getCustomer1(@PathVariable("custId") int custId) {
	  System.out.println("inside getCustomer ");
	  return rep.findById(custId);
	  
	  
	  }
	 
}
