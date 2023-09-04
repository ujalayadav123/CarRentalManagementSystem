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

import com.example.Entity.RentalBooking;
import com.example.Service.RentalBookingService;

@RestController
public class RentalBookingController {

	@Autowired
	RentalBookingService rentalBookingService;
	/*
	@PostMapping("/rentalBookings")
	public RentalBooking addNewRentalBooking(@RequestBody RentalBooking rentalBooking) {
		return rentalBookingService.addRentalBooking(rentalBooking);
	}
	*/
    @PostMapping("/rentalBookings")
    public ResponseEntity<?> addRentalBooking(@Valid @RequestBody RentalBooking rentalBooking, BindingResult result) {
        if (result.hasErrors()) {
            // Validation errors occurred, return error messages
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        // Calculate the total price and set it in the rentalBooking
        double totalPrice = rentalBooking.getPricePerDay() * rentalBooking.getNumberOfDays();
        rentalBooking.setTotalPrice(totalPrice);

        // Save the rentalBooking if data is valid
        RentalBooking savedRentalBooking = rentalBookingService.addRentalBooking(rentalBooking);
        return ResponseEntity.ok(savedRentalBooking);
    }

		@GetMapping("/rentalBookings")
	public List<RentalBooking> getAllRentalBookings() {
		return rentalBookingService.getAllRentalBooking();
	}
	@GetMapping("/rentalBooking/{id}")
	public RentalBooking getRentalBookingById(@PathVariable int id) {
		return rentalBookingService.findRentalBookingById(id);
	}
	@DeleteMapping("/rentalBookings/{id}")
	public String deleteRentalBooking(@PathVariable int id) {
		rentalBookingService.deleteRentalBooking(id);
		return "Booking is Deleted";
	}
	@PutMapping("/rentalBookings")
	public RentalBooking updateRentalBooking(@RequestBody RentalBooking rentalBooking) {
		return rentalBookingService.updateRentalBooking(rentalBooking);
	}
}



