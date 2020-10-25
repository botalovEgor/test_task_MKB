package com.mcb.creditfactory.dto;

import com.mcb.creditfactory.model.AssessedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirPlaneDto {
    private Long id;
    private String brand;
    private String model;
    private String manufacturer;
    private Short year;
    private Integer fuelCapacity;
    private Integer seats;
    private Set<AssessedValue> assessedValues;
}
