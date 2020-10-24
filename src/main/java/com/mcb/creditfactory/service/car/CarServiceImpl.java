package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public CarDto save(CarDto car) {
        return toDTO(carRepository.save(fromDto(car)));
    }

    @Override
    public CarDto load(Long id) {
        Car car = carRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Not found"));
        return toDTO(car);
    }

    public List<CarDto> findAllAsPresent(CarDto carDto){
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<Car> exampleQuery = Example.of(fromDto(carDto), matcher);
        List<Car> cars = carRepository.findAll(exampleQuery);
        return cars.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Car fromDto(CarDto dto) {
        return new Car(
                dto.getId(),
                dto.getBrand(),
                dto.getModel(),
                dto.getPower(),
                dto.getYear(),
                dto.getAssessedValues()
        );
    }

    @Override
    public CarDto toDTO(Car car) {
        return new CarDto(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getPower(),
                car.getYear(),
                "car",
                car.getAssessedValues()
        );
    }

}
