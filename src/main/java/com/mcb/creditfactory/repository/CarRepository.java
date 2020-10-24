package com.mcb.creditfactory.repository;

import com.mcb.creditfactory.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
