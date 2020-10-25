package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.CarDtoMapper;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final ExternalApproveService externalApproveService;

    private final CarRepository carRepository;

    private final CarDtoMapper mapper;

    @Override
    @Transactional
    public Long save(CarDto carDto) {
        Car car = mapper.fromDto(carDto);
        int approve = externalApproveService.approve(car);
        if (approve == 0) {
            carRepository.save(car);
        }
        return car.getId();
    }

    @Override
    public CarDto load(Long id) {
        Car car = carRepository.findById(id).orElse(null);
        return mapper.toDto(car);
    }

    public List<CarDto> findAllAsPresent(CarDto carDto) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<Car> exampleQuery = Example.of(mapper.fromDto(carDto), matcher);
        List<Car> cars = carRepository.findAll(exampleQuery);
        return cars.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public boolean addValue(CarDto carDto) {
        boolean result = false;

        Optional<Car> carOptional = carRepository.findById(carDto.getId());
        if(carOptional.isPresent()){
            result = carOptional.get().getAssessedValues().addAll(carDto.getAssessedValues());
        }

        return result;
    }
}
