package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import com.mcb.creditfactory.model.AssessedValue;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;

@AllArgsConstructor
public class CarAdapter implements CollateralObject {
    private CarDto car;


    @Override
    public BigDecimal getValue() {
        return getLastAssessedValue(car).getValue();
    }

    @Override
    public Short getYear() {
        return car.getYear();
    }

    @Override
    public LocalDate getDate() {
        return getLastAssessedValue(car).getEvaluationDate();
    }

    @Override
    public CollateralType getType() {
        return CollateralType.CAR;
    }

    private AssessedValue getLastAssessedValue(CarDto carDto) {
        return carDto.getAssessedValues().stream().max(Comparator.comparing(AssessedValue::getEvaluationDate)).get();
    }
}
