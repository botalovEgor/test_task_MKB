package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.external.CollateralType;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.service.airplane.AirPlaneService;
import com.mcb.creditfactory.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: reimplement this
@Service
public class CollateralService {
    @Autowired
    private CarService carService;

    @Autowired
    AirPlaneService airPlaneService;

    @Autowired
    ExternalApproveService externalApproveService;

    @SuppressWarnings("ConstantConditions")
    public Long saveCollateral(Collateral object) {
        int approved = externalApproveService.approve(object);
        if (!(approved == 0)) {
            return null;
        }

        Long id = null;
        CollateralType type = object.getType();
        if (type == CollateralType.CAR) {
            CarDto carDto = (CarDto) object;
            id = carService.save(carDto).getId();
        } else if (type == CollateralType.AIRPLANE) {
            AirPlaneDto airPlaneDto = (AirPlaneDto) object;
            id = airPlaneService.save(airPlaneDto).getId();
        }
        return id;
    }

    public Collateral getInfo(Collateral object) {
        Collateral collateral = null;

        CollateralType type = object.getType();
        if (type == CollateralType.CAR) {
            CarDto carDto = (CarDto) object;
            collateral = carService.load(carDto.getId());
        } else if (type == CollateralType.AIRPLANE) {
            AirPlaneDto airPlaneDto = (AirPlaneDto) object;
            collateral = airPlaneService.load(airPlaneDto.getId());
        }
        return collateral;
    }

    public Collateral addValue(Collateral object) {
        Collateral collateral = null;

        CollateralType type = object.getType();
        if (type == CollateralType.CAR) {
            CarDto carDto = (CarDto) object;
            collateral = carService.addValue(carDto);
        } else if (type == CollateralType.AIRPLANE) {
            AirPlaneDto airPlaneDto = (AirPlaneDto) object;
            collateral = airPlaneService.addValue(airPlaneDto);
        }
        return collateral;
    }
}
