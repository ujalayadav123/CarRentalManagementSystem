package com.example.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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

@Table(name = "SpringBootCar1")

public class Car {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    @NotBlank(message = "Car name is required") 
    @Column(name="carName")
    private String carName;
    
    @NotBlank(message = "Car brand is required")
    @Column(name="carBrand")
    private String carBrand;
    
    @Min(value = 1900, message = "Model year must be at least 1900")
    @Max(value = 3000, message = "Model year must be less than or equal to 3000")
    @Column(name="modelYear")
    private int modelYear;
    
    @NotBlank(message = "Number plate is required")
    @Column(name="numberPlate")
     private String numberPlate;
    
    @NotBlank(message = "Color is required")
    @Column(name="color")
    private String color;

    @Min(value = 0, message = "Price per day must be non-negative")
    @Column(name = "price_per_day")
    private double pricePerDay;

    // Other attributes, getters, setters, and constructors.

    // Example of relationships (assuming one-to-many between Car and RentalBooking)
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "car")
    private List<RentalBooking> rentalBookings;

    public Car(int id) {
        this.id = id;
    }

     
    @Builder
	public Car(int id,  String carName,String carBrand,int modelYear,String numberPlate,String color,double pricePerDay) {
		super();
		this.id = id;
		this.carName = carName;
		this.carBrand = carBrand;
		this.modelYear = modelYear;
		this.numberPlate = numberPlate;
		this.color = color;
		this.pricePerDay = pricePerDay;
	}
 }
