package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.service.airplane.AirPlaneService;
import com.mcb.creditfactory.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CollateralService {
    @Autowired
    private CarService carService;

    @Autowired
    private AirPlaneService airPlaneService;

    @Autowired
    ExternalApproveService externalApproveService;

    public Long saveCollateral(Collateral object) {
        Long id = null;

        if (externalApproveService.approve(new CollateralObjectAdapter(object)) != 0) {
            return id;
        }

        if (object.getType().equals("car")) {
            CarDto car = (CarDto) object;
            id = carService.save(car).getId();

        } else if (object.getType().equals("airPlane")) {
            AirPlaneDto airPlane = (AirPlaneDto) object;
            id = airPlaneService.save(airPlane).getId();
        }
        return id;
    }

    public Collateral getInfo(Collateral object) {

        if (object.getType().equals("car")) {
            CarDto car = (CarDto) object;
            object = carService.load(car.getId());

        } else if (object.getType().equals("airPlane")) {
            AirPlaneDto airPlane = (AirPlaneDto) object;
            object = airPlaneService.load(airPlane.getId());
        }
        return object;
    }
}
