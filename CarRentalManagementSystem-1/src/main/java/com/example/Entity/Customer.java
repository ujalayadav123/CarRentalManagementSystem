package com.example.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component

@Table(name = "SpringBootcustomers1")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "customerAge")
    private int customerAge;
    
    @NotEmpty(message = "Name can't be empty!")
	@Size(min = 2, max = 25, message = "Please enter proper name")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Accepts only alphabets! re-enter the Name")
    @Column(name = "customerName")
    private String customerName;
    
    @NotBlank(message = "must type Value in email of customer")
	@Email(message = "Invalid email of customer")
	@Column(unique = true)
    private String customerEmail;
   
    @NotBlank(message = "License number is required")
    @Column(name = "licenceNumber")
    private String licenceNumber;
  
    @NotBlank(message = "National ID number is required")
    @Pattern(regexp = "[0-9]{10}", message = "National ID number must be 10 digits")
    @Column(name = "NationIDNumber")
    private String NationIDNumber;
 
    @NotBlank(message = "customerBooking  is required")
    @Column(name = "customerBooking")
    private String customerBooking;
    
    // Other attributes, getters, setters, and constructors.

    // Example of relationships (assuming one-to-many between Customer and RentalBooking)
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "customer")
  //  @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")

    private List<RentalBooking> rentalBookings;

    public Customer(int id) {
        this.id = id;
    }
    
    @Builder
	public Customer(int id, int customerAge,String customerName,String customerEmail,String licenceNumber,
			String NationIDNumber,String customerBooking) {
		super();
		this.id = id;
		this.customerAge = customerAge;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.licenceNumber = licenceNumber;
		this.NationIDNumber = NationIDNumber;
		this.customerBooking = customerBooking;
	}
    
	

    
}
