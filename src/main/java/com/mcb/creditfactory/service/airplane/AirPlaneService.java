package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.model.AirPlane;

import java.util.List;

public interface AirPlaneService {
    AirPlaneDto save(AirPlaneDto airPlane);
    AirPlaneDto load(Long id);
    AirPlane fromDto(AirPlaneDto dto);
    AirPlaneDto toDTO(AirPlane airPlane);

    List<AirPlaneDto> findAllAsPresent(AirPlaneDto airPlaneDto);
}
