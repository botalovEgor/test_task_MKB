package com.mcb.creditfactory.dto;


import com.mcb.creditfactory.model.AirPlane;
import org.mapstruct.Mapper;

@Mapper
public interface AirplaneDtoMapper {
    AirPlaneDto toDto(AirPlane airPlane);

    AirPlane fromDto(AirPlaneDto airPlaneDto);
}
