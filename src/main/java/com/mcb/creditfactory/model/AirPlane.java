package com.mcb.creditfactory.model;

import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AIRPLANE")
public class AirPlane implements CollateralObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    private String brand;

    private String model;

    private String manufacturer;

    @Column(name = "year_of_issue")
    private Short year;

    private Integer fuelCapacity;

    private Integer seats;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "AIRPLANE_ASSESSED_VALUES",
            joinColumns = @JoinColumn(name = "airplane_id"),
            inverseJoinColumns = @JoinColumn(name = "assessed_value_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<AssessedValue> assessedValues = new HashSet<>();

    @Override
    public BigDecimal getValue() {
        return getLastAssessedValue().getValue();
    }

    @Override
    public LocalDate getDate() {
        return getLastAssessedValue().getEvaluationDate();
    }

    @Override
    public CollateralType getType() {
        return CollateralType.AIRPLANE;
    }

    private AssessedValue getLastAssessedValue() {
        return getAssessedValues().stream().max(Comparator.comparing(AssessedValue::getEvaluationDate))
                .orElseThrow(()->new IllegalArgumentException("value not found"));
    }

}
