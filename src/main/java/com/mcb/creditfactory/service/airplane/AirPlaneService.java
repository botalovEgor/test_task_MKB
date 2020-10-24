package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.model.AirPlane;
import com.mcb.creditfactory.service.BaseCrudService;

import java.util.List;

public interface AirPlaneService extends BaseCrudService<AirPlaneDto, AirPlane, Long> {
    AirPlaneDto save(AirPlaneDto airPlane);
    AirPlaneDto load(Long id);
    AirPlane fromDto(AirPlaneDto dto);
    AirPlaneDto toDTO(AirPlane airPlane);

    List<AirPlaneDto> findAllAsPresent(AirPlaneDto airPlaneDto);
}
