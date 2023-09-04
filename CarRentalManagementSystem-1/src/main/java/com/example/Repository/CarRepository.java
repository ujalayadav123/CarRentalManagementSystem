package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{
	List<Car> findByCarName(String carName);
	List<Car> findByCarBrand(String carBrand);


}
