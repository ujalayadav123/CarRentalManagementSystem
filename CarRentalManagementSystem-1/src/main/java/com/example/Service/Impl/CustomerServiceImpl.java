package com.example.Service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Customer;
import com.example.Repository.CustomerRepository;
import com.example.Service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}
				
	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public Customer findCustomerById(int id) {
		// TODO Auto-generated method stub
		return customerRepository.findById(id).get();
	}

	@Override
	@Transactional
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Customer _customer= customerRepository.findById(customer.getId()).get();
		_customer.setCustomerAge(customer.getCustomerAge());
		_customer.setCustomerName(customer.getCustomerName());
		_customer.setCustomerEmail(customer.getCustomerName());
		_customer.setLicenceNumber(customer.getLicenceNumber());
		_customer.setNationIDNumber(customer.getNationIDNumber());
		_customer.setCustomerBooking(customer.getCustomerBooking());
		_customer.setRentalBookings(customer.getRentalBookings());
		Customer managedCustomer = customerRepository.save(customer);
        return managedCustomer;

//			return customerRepository.save(_customer);
	}
		
	@Override
	public Void deleteCustomer(int id) {
		// TODO Auto-generated method stub
		 customerRepository.deleteById(id);
		return null;
	}
	
}
