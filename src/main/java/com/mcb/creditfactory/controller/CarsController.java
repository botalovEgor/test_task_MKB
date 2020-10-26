package com.mcb.creditfactory.controller;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.service.car.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarsController {

    private final CarService service;

    @PostMapping("/collateral/car/save")
    public HttpEntity<Long> save(@RequestBody CarDto carDto) {
        Long id = service.save(carDto);

        URI uri = MvcUriComponentsBuilder
                .fromMethodName(CarsController.class, "getInfo", id)
                .build().encode().toUri();

        return id != null ? ResponseEntity.created(uri).body(id) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/collateral/car/{id}")
    public HttpEntity<CarDto> getInfo(@PathVariable("id") Long id) {
        CarDto carDto = service.load(id);
        return ResponseEntity.ok(carDto);
    }

    @PatchMapping("/collateral/car/newValue")
    public HttpEntity<Long> addNewEstimation(@RequestBody CarDto carDto) {
        boolean result = service.addNewEstimation(carDto);
        return result ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PostMapping("/collateral/car/findAs")
    public HttpEntity<List<CarDto>> findAs(@RequestBody CarDto carDto) {
        List<CarDto> carDtos = service.findAllAsPresent(carDto);
        return !carDtos.isEmpty() ? ResponseEntity.ok(carDtos) : ResponseEntity.notFound().build();
    }
}
