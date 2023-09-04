package com.example.Service.Impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.RentalBooking;
import com.example.Repository.RentalBookingRepository;
import com.example.Service.RentalBookingService;

@Service
public class RentalBookingServiceImpl implements RentalBookingService{
	
	@Autowired
	RentalBookingRepository rentalBookingRepository;

	@Override
/*	public RentalBooking addRentalBooking(RentalBooking rentalBooking) {
		// TODO Auto-generated method stub
		return rentalBookingRepository.save(rentalBooking);
	}
*/
	public RentalBooking addRentalBooking(RentalBooking rentalBooking) {
        // Calculate NumberOfDays and totalPrice
        LocalDate rentDate = rentalBooking.getDateOfRent();
        LocalDate returnDate = rentalBooking.getDateOfReturn();
        int numberOfDays = (int) ChronoUnit.DAYS.between(rentDate, returnDate);
        double totalPrice = numberOfDays * rentalBooking.getPricePerDay();

        rentalBooking.setNumberOfDays(numberOfDays);
        rentalBooking.setTotalPrice(totalPrice);

		return rentalBookingRepository.save(rentalBooking);
    }
	@Override
	public List<RentalBooking> getAllRentalBooking() {
		// TODO Auto-generated method stub
		return rentalBookingRepository.findAll();
	}

	@Override
	public RentalBooking findRentalBookingById(int id) {
		// TODO Auto-generated method stub
		return rentalBookingRepository.findById(id).get();
	}

	@Override
	public RentalBooking updateRentalBooking(RentalBooking rentalBooking) {
		// TODO Auto-generated method stub
		RentalBooking _rentalBooking= rentalBookingRepository.findById(rentalBooking.getId()).get();
	//	RentalBooking _rentalBooking=rentalBookingRepository.findById((int) rentalBooking.ge
		_rentalBooking.setDateOfRent(rentalBooking.getDateOfRent());
		_rentalBooking.setDateOfReturn(rentalBooking.getDateOfReturn());
		_rentalBooking.setNumberOfDays(rentalBooking.getNumberOfDays());
		_rentalBooking.setPricePerDay(rentalBooking.getPricePerDay());
		_rentalBooking.setTotalPrice(rentalBooking.getTotalPrice());
		_rentalBooking.setCar(rentalBooking.getCar());
		_rentalBooking.setCustomer(rentalBooking.getCustomer());
		return null;
	}

	@Override
	public Void deleteRentalBooking(int id) {
		// TODO Auto-generated method stub
		rentalBookingRepository.deleteById(id);
		return null;
	}


}
