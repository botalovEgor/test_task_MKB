package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirPlaneDto;

import java.util.List;

public interface AirPlaneService {
    Long save(AirPlaneDto airPlane);
    AirPlaneDto load(Long id);
    boolean addNewEstimation(AirPlaneDto airPlaneDto);
    List<AirPlaneDto> findAllAsPresent(AirPlaneDto airPlaneDto);
}
