package com.mcb.creditfactory.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mcb.creditfactory.model.AssessedValue;

import java.util.Set;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CarDto.class, name = "car"),
        @JsonSubTypes.Type(value = AirPlaneDto.class, name = "airPlane")
})
public interface Collateral {
    String getType();

    Set<AssessedValue> getAssessedValues();

    Short getYear();

}
