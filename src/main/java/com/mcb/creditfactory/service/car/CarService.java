package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.model.Car;

public interface CarService {
    CarDto save(CarDto carDto);
    CarDto load(Long id);
    Car fromDto(CarDto dto);
    CarDto toDTO(Car car);
    Long getId(Car car);
    Collateral addValue(CarDto carDto);
}
