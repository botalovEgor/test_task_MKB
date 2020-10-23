package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import com.mcb.creditfactory.model.AssessedValue;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;

@AllArgsConstructor
public class AirPlaneAdapter implements CollateralObject {
    private AirPlaneDto airPlaneDto;

    @Override
    public BigDecimal getValue() {
        return getLastAssessedValue(airPlaneDto).getValue();

    }

    @Override
    public Short getYear() {
        return airPlaneDto.getYear();
    }

    @Override
    public LocalDate getDate() {
        return getLastAssessedValue(airPlaneDto).getEvaluationDate();
    }

    @Override
    public CollateralType getType() {
        return CollateralType.AIRPLANE;
    }

    private AssessedValue getLastAssessedValue(AirPlaneDto airPlaneDto) {
        return airPlaneDto.getAssessedValues().stream().max(Comparator.comparing(AssessedValue::getEvaluationDate)).get();
    }
}
