package com.mcb.creditfactory.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mcb.creditfactory.model.AssessedValue;

import java.util.Set;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "jsonType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CarDto.class, name = "car"),
        @JsonSubTypes.Type(value = AirPlaneDto.class, name = "airPlane")
})
public interface Collateral {
    Long getId();

    String getType();

    Set<AssessedValue> getAssessedValues();

    Short getYear();

}
