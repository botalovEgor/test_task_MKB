package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import com.mcb.creditfactory.model.AssessedValue;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;

@AllArgsConstructor
public class CollateralObjectAdapter implements CollateralObject {
    private final Collateral collateral;

    @Override
    public BigDecimal getValue() {
        return getLastAssessedValue(collateral).getValue();
    }

    @Override
    public Short getYear() {
        return collateral.getYear();
    }

    @Override
    public LocalDate getDate() {
        return getLastAssessedValue(collateral).getEvaluationDate();
    }

    @Override
    public CollateralType getType() {
        return CollateralType.valueOf(collateral.getType().toUpperCase());
    }

    private AssessedValue getLastAssessedValue(Collateral collateral) {
        return collateral.getAssessedValues().stream().max(Comparator.comparing(AssessedValue::getEvaluationDate))
                .orElseThrow(()->new IllegalArgumentException("value not found"));
    }
}
