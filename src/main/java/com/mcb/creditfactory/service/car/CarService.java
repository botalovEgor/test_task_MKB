package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;

import java.util.List;

public interface CarService {
    Long save(CarDto car);
    CarDto load(Long id);
    boolean addValue(CarDto carDto);
    List<CarDto> findAllAsPresent(CarDto carDto);
}
