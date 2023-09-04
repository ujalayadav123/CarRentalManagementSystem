package com.example.Service;

import java.util.List;

import com.example.Entity.Car;

public interface CarService {

	public Car addCar(Car car);
	public List<Car> getAllCar();
	public Car findCarById(int id);
	public List<Car> searchByCarName(String carName);
	public List<Car> searchByCarBrand(String carBrand);
	public Car updateCar(Car car);
	public Void deleteCar(int id);
}
