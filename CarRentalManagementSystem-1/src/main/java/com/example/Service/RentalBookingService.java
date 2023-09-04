package com.example.Service;

import java.util.List;

import com.example.Entity.RentalBooking;


public interface RentalBookingService {

	public RentalBooking addRentalBooking(RentalBooking rentalBooking);
	public List<RentalBooking> getAllRentalBooking();
	public RentalBooking findRentalBookingById(int id);
	public RentalBooking updateRentalBooking(RentalBooking rentalBooking);
	public Void deleteRentalBooking(int id);

}
