package com.mcb.creditfactory.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AIRPLAN")
public class AirPlane {
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

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "AIRPLANE_ASSESSED_VALUE",
            joinColumns = @JoinColumn(name = "airplane_id"),
            inverseJoinColumns = @JoinColumn(name = "assessed_value_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<AssessedValue> assessedValues = new HashSet<>();

}
