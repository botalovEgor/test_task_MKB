package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.model.AirPlane;

import java.util.Optional;

public interface AirPlaneService {
    AirPlaneDto save(AirPlaneDto airPlaneDto);
    AirPlaneDto load(Long id);
    AirPlane fromDto(AirPlaneDto dto);
    AirPlaneDto toDTO(AirPlane airPlane);
    Long getId(AirPlane airPlane);
    Collateral addValue(AirPlaneDto airPlaneDto);
}
