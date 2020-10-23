package com.mcb.creditfactory.repository;

import com.mcb.creditfactory.model.AirPlane;
import org.springframework.data.repository.CrudRepository;

public interface AirPlaneRepository extends CrudRepository<AirPlane, Long> {
    AirPlane findByBrandAndModelAndManufacturerAndYearAndFuelCapacityAndSeats(String brand, String model, String manufacturer, Short year, Integer fuelCapacity, Integer seats);
}
