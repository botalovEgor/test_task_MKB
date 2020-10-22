package com.mcb.creditfactory.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mcb.creditfactory.external.CollateralObject;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CarDto.class, name = "car"),
        @JsonSubTypes.Type(value = AirPlaneDto.class, name = "airPlane")
})
public interface Collateral extends CollateralObject {
}
