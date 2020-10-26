package com.mcb.creditfactory.controller;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.service.airplane.AirPlaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AirPlanesController {

    private final AirPlaneService service;

    @PostMapping("/collateral/airPlane/save")
    public HttpEntity<Long> save(@RequestBody AirPlaneDto airPlaneDto) {
        Long id = service.save(airPlaneDto);

        URI uri = MvcUriComponentsBuilder
                .fromMethodName(AirPlanesController.class, "getInfo", id)
                .build().encode().toUri();

        return id != null ? ResponseEntity.created(uri).body(id) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/collateral/airPlane/{id}")
    public HttpEntity<AirPlaneDto> getInfo(@PathVariable("id") Long id) {
        AirPlaneDto airPlaneDto = service.findById(id);
        return ResponseEntity.ok(airPlaneDto);
    }

    @PatchMapping("/collateral/airPlane/newValue")
    public HttpEntity<Long> addNewEstimation(@RequestBody AirPlaneDto airPlaneDto) {
        boolean result = service.addNewEstimation(airPlaneDto);
        return result ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PostMapping("/collateral/airPlane/findAs")
    public HttpEntity<List<AirPlaneDto>> findAs(@RequestBody AirPlaneDto airPlaneDto) {
        List<AirPlaneDto> airPlaneDtos = service.findAllAsPresent(airPlaneDto);
        return !airPlaneDtos.isEmpty() ? ResponseEntity.ok(airPlaneDtos) : ResponseEntity.notFound().build();
    }
}
