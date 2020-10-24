package com.mcb.creditfactory.service;

import com.mcb.creditfactory.service.airplane.AirPlaneService;
import com.mcb.creditfactory.service.car.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CrudServiceFactory {

    private final CarService carService;


    private final AirPlaneService airPlaneService;

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
