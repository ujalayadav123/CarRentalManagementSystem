package com.example.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Car;
import com.example.Repository.CarRepository;
import com.example.Service.CarService;

@Service
public class CarServiceImpl implements CarService{
	
	@Autowired
	CarRepository carRepository;

	@Override
	public Car addCar(Car car) {
		// TODO Auto-generated method stub
		return carRepository.save(car);
	}

	@Override
	public List<Car> getAllCar() {
		// TODO Auto-generated method stub
		return carRepository.findAll();
	}

	@Override
	public Car findCarById(int id) {
		// TODO Auto-generated method stub
		return carRepository.findById(id).get();
	}

	@Override
	public Car updateCar(Car car) {
		// TODO Auto-generated method stub
		Car _car= carRepository.findById(car.getId()).get();
		_car.setCarName(car.getCarName());
		_car.setCarBrand(car.getCarBrand());
		_car.setModelYear(car.getModelYear());
		_car.setNumberPlate(car.getNumberPlate());
		_car.setColor(car.getColor());
		_car.setPricePerDay(car.getPricePerDay());
        Car managedCar = carRepository.save(car);
        return managedCar;

	//	return carRepository.save(_car);
	}
	
	@Override
	public Void deleteCar(int id) {
		// TODO Auto-generated method stub
		Optional<Car> op = carRepository.findById(id);
		if (op.isPresent())

		{
			carRepository.deleteById(id);

		} else {
			System.out.println("Car is not available");
		}
		return null;
	}

	@Override
	public List<Car> searchByCarName(String carName) {
		// TODO Auto-generated method stub
		return carRepository.findByCarName(carName);
	}

	@Override
	public List<Car> searchByCarBrand(String carBrand) {
		// TODO Auto-generated method stub
		return carRepository.findByCarBrand(carBrand);
	}
}
