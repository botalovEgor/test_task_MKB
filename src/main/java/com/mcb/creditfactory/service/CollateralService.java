package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.AssessedValue;
import com.mcb.creditfactory.service.airplane.AirPlaneService;
import com.mcb.creditfactory.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


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
            object = carService.findAs(car);

        } else if (object.getType().equals("airPlane")) {
            AirPlaneDto airPlane = (AirPlaneDto) object;
            object = airPlaneService.findAs(airPlane);
        }
        return object;
    }

    @Transactional
    public boolean addValue(Collateral object) {
        boolean result = false;

        Set<AssessedValue> assessedValues = object.getAssessedValues();

        if (object.getType().equals("car")) {
            CarDto car = (CarDto) object;
            result = carService.load(car.getId()).getAssessedValues().addAll(assessedValues);

        } else if (object.getType().equals("airPlane")) {
            AirPlaneDto airPlane = (AirPlaneDto) object;
            result = airPlaneService.load(airPlane.getId()).getAssessedValues().addAll(assessedValues);
        }
        return result;
    }
}
