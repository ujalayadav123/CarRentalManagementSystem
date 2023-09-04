package com.example.Service;

import java.util.List;

import com.example.Entity.Customer;


public interface CustomerService {

	public Customer addCustomer(Customer customer);
	public List<Customer> getAllCustomer();
	public Customer findCustomerById(int id);
	public Customer updateCustomer(Customer customer);
	public Void deleteCustomer(int id);
	
}
