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

import com.example.Entity.Car;
import com.example.Service.CarService;


@RestController
public class CarController {
	@Autowired
	CarService carService;
	
/*	@PostMapping("/cars")
	public Car addNewCar(@RequestBody Car car) {
		return carService.addCar(car);
	}*/

    @PostMapping("/cars")
    public ResponseEntity<?> saveOrUpdateCar(@Valid @RequestBody Car car, BindingResult result) {
        if (result.hasErrors()) {
            // Validation errors occurred, return error messages
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Car savedCar = carService.updateCar(car);
        return ResponseEntity.ok(savedCar);
    }

		@GetMapping("/cars")
	public List<Car> getAllCars() {
		return carService.getAllCar();
	}
	@GetMapping("/cars/{id}")
	public Car getCarById(@PathVariable int id) {
		return carService.findCarById(id);
	}
	@DeleteMapping("/cars/{id}")
	public String deleteCar(@PathVariable int id) {
		carService.deleteCar(id);
		return "Car is Deleted";
	}
	@PutMapping("/cars")
	public Car updateCar(@RequestBody Car car) {
		return carService.updateCar(car);
	}
}



