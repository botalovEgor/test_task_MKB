package com.mcb.creditfactory.service;

import com.mcb.creditfactory.service.airplane.AirPlaneService;
import com.mcb.creditfactory.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CrudServiceFactory {
    @Autowired
    CarService carService;

    @Autowired
    AirPlaneService airPlaneService;

    BaseCrudService getService(String type) {
        if (type.equalsIgnoreCase("car")) {
            return carService;
        } else if (type.equalsIgnoreCase("airPlane")) {
            return airPlaneService;
        } else {
            throw new IllegalArgumentException("unknown type of collateral object");
        }
    }
}
