package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
public class AirPlaneAdapter implements CollateralObject {
    private AirPlaneDto airPlaneDto;

    @Override
    public BigDecimal getValue() {
        return airPlaneDto.getLastValue();
    }

    @Override
    public Short getYear() {
        return airPlaneDto.getYear();
    }

    @Override
    public LocalDate getDate() {
        return airPlaneDto.getValueDate();
    }

    @Override
    public CollateralType getType() {
        return CollateralType.CAR;
    }
}
