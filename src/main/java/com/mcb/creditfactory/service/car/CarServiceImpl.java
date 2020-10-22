package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.AssessedValue;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private ExternalApproveService approveService;

    @Autowired
    private CarRepository carRepository;

    @Override
    public boolean approve(CarDto dto) {
        return approveService.approve(new CarAdapter(dto)) == 0;
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Optional<Car> load(Long id) {
        return carRepository.findById(id);
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
        assessedValue.setEvaluationDate(LocalDate.now());
        assessedValue.setValue(dto.getLastValue());

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
                last.getValue(),
                last.getEvaluationDate()
        );
    }

    @Override
    public Long getId(Car car) {
        return car.getId();
    }

    private AssessedValue getLastAssessedValue(Car car){
        return car.getAssessedValues().stream().max(Comparator.comparing(AssessedValue::getEvaluationDate)).get();
    }
}
