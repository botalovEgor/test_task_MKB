package com.mcb.creditfactory.repository;

import com.mcb.creditfactory.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {

    Car findByBrandAndModelAndPowerAndYear(String brand, String model, Double power, Short year);

}
