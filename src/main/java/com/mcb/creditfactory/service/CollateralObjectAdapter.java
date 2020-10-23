package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import com.mcb.creditfactory.model.AssessedValue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;

public class CollateralObjectAdapter implements CollateralObject {
    private Collateral collateral;

    public CollateralObjectAdapter(Collateral collateral) {
        this.collateral = collateral;
    }

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
        return collateral.getAssessedValues().stream().max(Comparator.comparing(AssessedValue::getEvaluationDate)).get();
    }
}
