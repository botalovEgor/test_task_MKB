package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.external.CollateralType;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.AssessedValue;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public CarDto save(CarDto cardto) {
        return toDTO(carRepository.save(fromDto(cardto)));
    }

    @Override
    public CarDto load(Long id) {
        Car car =  carRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(String.format("Car with id %d not found", id)));

        return toDTO(car);
    }

    @Override
    public Car fromDto(CarDto dto) {
        Car car = new Car();
        car.setId(dto.getId());
        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setPower(dto.getPower());
        car.setYear(dto.getYear());

        AssessedValue assessedValue = new AssessedValue();
        assessedValue.setEvaluationDate(dto.getDate());
        assessedValue.setValue(dto.getValue());

        Set<AssessedValue> assessedValues = new HashSet<>();
        assessedValues.add(assessedValue);

        car.setAssessedValues(assessedValues);

        return car;
    }

    @Override
    public CarDto toDTO(Car car) {
        AssessedValue last = getLastAssessedValue(car);

        return new CarDto(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getPower(),
                car.getYear(),
                last.getId(),
                last.getValue(),
                last.getEvaluationDate(),
                CollateralType.CAR
        );
    }

    @Override
    public Long getId(Car car) {
        return car.getId();
    }

    @Override
    public Collateral addValue(CarDto carDto) {
        AssessedValue newAssessedValue = new AssessedValue();
        newAssessedValue.setEvaluationDate(carDto.getDate());
        newAssessedValue.setValue(carDto.getValue());

        Car car = carRepository.findById(carDto.getId())
                .orElseThrow(()-> new IllegalArgumentException("Car not found"));
        car.getAssessedValues().add(newAssessedValue);
        carDto.setValueId(getLastAssessedValue(car).getId());
        return carDto;
    }

    private AssessedValue getLastAssessedValue(Car car){
        return car.getAssessedValues().stream().max(Comparator.comparing(AssessedValue::getEvaluationDate)).get();
    }
}
