package com.mcb.creditfactory.dto;

import com.mcb.creditfactory.model.AssessedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    private Long id;
    private String brand;
    private String model;
    private Double power;
    private Short year;
    private Set<AssessedValue> assessedValues;
}
