package com.mcb.creditfactory.controller;

import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.service.CollateralService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class CollateralObjectController {

    private final CollateralService service;

    @PostMapping("/collateral/save")
    public HttpEntity<Long> save(@RequestBody Collateral object) {
        Long id = service.saveCollateral(object);

        URI uri = MvcUriComponentsBuilder
                .fromMethodName(CollateralObjectController.class, "getInfo", object.getType(), id)
                .build().encode().toUri();

        return id != null ? ResponseEntity.created(uri).body(id) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/collateral/info")
    public HttpEntity<Collateral> getInfo(@RequestParam("type") String type,
                                          @RequestParam("id") Long id) {
        Collateral info = service.getInfo(type, id);
        return info != null ? ResponseEntity.ok(info) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/collateral/newValue")
    public HttpEntity<Collateral> addValue(@RequestBody Collateral object) {
        boolean result = service.addValue(object);
        return result ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
