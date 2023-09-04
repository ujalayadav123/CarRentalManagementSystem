package com.example.Entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SpringBootrental_bookings1")
public class RentalBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(cascade = CascadeType.MERGE)   
//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @NotNull(message = "Date of rent is required")
    private LocalDate dateOfRent;

    @NotNull(message = "Date of return is required")
    private LocalDate dateOfReturn;

 //   @Min(value = 1, message = "Number of days must be at least 1")
    private int numberOfDays;

    @Min(value = 0, message = "Price per day must be non-negative")
    private double pricePerDay;

  //  @NotNull(message = "Total price is required")
    private Double totalPrice;

     public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public LocalDate getDateOfRent() {
		return dateOfRent;
	}
	public void setDateOfRent(LocalDate dateOfRent) {
		this.dateOfRent = dateOfRent;
	}
	public  LocalDate getDateOfReturn() {
		return dateOfReturn;
	}
	public void setDateOfReturn(LocalDate dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}
	public int getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	public double getPricePerDay() {
		return pricePerDay;
	}
	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Builder
    public RentalBooking(Car car, Customer customer,LocalDate dateOfRent,LocalDate dateOfReturn,int numberOfDays,double pricePerDay,Double totalPrice ) {
		super();
		this.car=car;
		this.customer = customer;
		this.dateOfRent = dateOfRent;
		this.dateOfReturn = dateOfReturn;
		this.numberOfDays = numberOfDays;
		this.pricePerDay = pricePerDay;
		this.totalPrice = totalPrice;
	}
    
    
}