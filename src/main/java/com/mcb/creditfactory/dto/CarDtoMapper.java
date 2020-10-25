package com.mcb.creditfactory.dto;

import com.mcb.creditfactory.model.Car;
import org.mapstruct.Mapper;

@Mapper
public interface CarDtoMapper {
    CarDto toDto(Car car);

    Car fromDto(CarDto carDto);
}
