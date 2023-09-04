package com.example.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Customer;
import com.example.Service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;
	/*
	@PostMapping("/customers")
	public Customer addNewCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}
	*/
    @PostMapping("/customers")
    public ResponseEntity<?> saveOrUpdateCar(@Valid @RequestBody Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            // Validation errors occurred, return error messages
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Customer savedCustomer = customerService.updateCustomer(customer);
        return ResponseEntity.ok(savedCustomer);
    }


		@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomer();
	}
	@GetMapping("/customers/{id}")
	public Customer getCustomerById(@PathVariable int id) {
		return customerService.findCustomerById(id);
	}
	@DeleteMapping("/customers/{id}")
	public String deleteCustomer(@PathVariable int id) {
		customerService.deleteCustomer(id);
		return "Customer is Deleted";
	}
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer) {
		return customerService.updateCustomer(customer);
	}
}



