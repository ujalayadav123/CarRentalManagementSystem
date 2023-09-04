package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.Entity.Car;
import com.example.Entity.Customer;
import com.example.Entity.RentalBooking;
import com.example.ExceptionHandling.CustomerNotFoundException;
import com.example.Repository.CustomerRepository;
import com.example.Service.CustomerService;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerServiceTest {
	@Autowired 
	CustomerService isvc;
	
	@MockBean
	CustomerRepository adao;
	private Car car;
	private RentalBooking rentalBooking;
	private Customer customer, customer11;
	
	@BeforeEach
	void init() {
		car=car.builder().carName("").carBrand("").modelYear(2022).numberPlate("").color("").pricePerDay(2500).build();
		
		rentalBooking=rentalBooking.builder().dateOfRent(LocalDate.of(2023, 1, 1)).dateOfReturn(LocalDate.of(2023, 1, 3))
				.numberOfDays(2).pricePerDay(2500).totalPrice((double) 5000).build();
		
		customer11=customer11.builder().id(101).customerAge(24).customerName("Supriya").customerEmail("supriya@gmail.com")
				.licenceNumber("PU027393").NationIDNumber("875848589435").build();
		
	//	customer = customer.builder().id((long) 102).customerAge(26).customerName("Sonu").customerEmail("sonu@gmail.com")
	//	        .licenceNumber("PU0376783").NationIDNumber("784983932").build();

		customer=customer.builder().id(102).customerAge(26).customerName("Sonu").customerEmail("sonu@gmail.com")
				.licenceNumber("PU0376783").NationIDNumber("784983932").build();
		
		
	}
	
	@Test
	void testAddCustomer() {
		Mockito.when(adao.save(customer)).thenReturn(customer);
		assertEquals(customer, isvc.addCustomer(customer));
	}
	@Test
	void testGetCustomer() throws CustomerNotFoundException{
		
		Optional<Customer> customer1 = Optional.of(customer);
		Mockito.when(adao.findById(102)).thenReturn(customer1);

		assertEquals("Sonu", isvc.findCustomerById(102).getCustomerName());
	}
	@Test
	void testGetAll() {
		List<Customer> lcustomer=Stream.of(customer, customer11).collect(Collectors.toList());
		Mockito.when(adao.findAll()).thenReturn(lcustomer);
		
		assertEquals(2, isvc.getAllCustomer().size());
	}
	@Test
	void testDeleteCustomer() {
	//	try {
		isvc.deleteCustomer(102);
		Mockito.verify(adao).deleteById(102);
	}// catch (CustomerNotFoundException e) {
	//	e.printStackTrace();
	//}
// }
}