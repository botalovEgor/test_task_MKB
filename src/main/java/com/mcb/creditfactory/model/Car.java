package com.mcb.creditfactory.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CAR")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private Double power;

    @Column(name = "year_of_issue")
    private Short year;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "CAR_ASSESSED_VALUES",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "assessed_value_id"))
    @Fetch(FetchMode.JOIN)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Column(name = "assessed_value")
    private Set<AssessedValue> assessedValues = new HashSet<>();
}
