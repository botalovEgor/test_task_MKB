package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.service.BaseCrudService;

import java.util.List;

public interface CarService extends BaseCrudService<CarDto, Car, Long> {
    CarDto save(CarDto car);
    CarDto load(Long id);
    Car fromDto(CarDto dto);
    CarDto toDTO(Car car);
    List<CarDto> findAllAsPresent(CarDto carDto);
}
