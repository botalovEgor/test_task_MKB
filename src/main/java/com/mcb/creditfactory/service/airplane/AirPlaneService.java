package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.model.AirPlane;

import java.util.Optional;

public interface AirPlaneService {
    boolean approve(AirPlaneDto dto);
    AirPlane save(AirPlane airPlane);
    Optional<AirPlane> load(Long id);
    AirPlane fromDto(AirPlaneDto dto);
    AirPlaneDto toDTO(AirPlane airPlane);
    Long getId(AirPlane airPlane);
}
