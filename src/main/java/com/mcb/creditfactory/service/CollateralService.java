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

        if (object.getType().equalsIgnoreCase("car")) {
            CarDto car = (CarDto) object;
            id = carService.save(car).getId();

        } else if (object.getType().equalsIgnoreCase("airPlane")) {
            AirPlaneDto airPlane = (AirPlaneDto) object;
            id = airPlaneService.save(airPlane).getId();
        }
        return id;
    }

    public Collateral getInfo(String type, Long id) {
        Collateral collateral = null;
        if (type.equalsIgnoreCase("car")) {
            collateral = carService.load(id);

        } else if (type.equalsIgnoreCase("airPlane")) {
            collateral = airPlaneService.load(id);
        }
        return collateral;
    }

    @Transactional
    public boolean addValue(Collateral object) {
        boolean result = false;

        Set<AssessedValue> assessedValues = object.getAssessedValues();

        if (object.getType().equalsIgnoreCase("car")) {
            CarDto car = (CarDto) object;
            result = carService.load(car.getId()).getAssessedValues().addAll(assessedValues);

        } else if (object.getType().equalsIgnoreCase("airPlane")) {
            AirPlaneDto airPlane = (AirPlaneDto) object;
            result = airPlaneService.load(airPlane.getId()).getAssessedValues().addAll(assessedValues);
        }
        return result;
    }
}
