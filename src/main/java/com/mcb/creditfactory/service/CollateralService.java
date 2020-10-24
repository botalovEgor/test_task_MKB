package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.AssessedValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class CollateralService {
    private final CrudServiceFactory crudServiceFactory;

    private final ExternalApproveService externalApproveService;

    @Transactional
    public Long saveCollateral(Collateral object) {
        Long id = null;

        if (externalApproveService.approve(new CollateralObjectAdapter(object)) != 0) {
            return id;
        }

        CrudService<Collateral, ?, Long> service = crudServiceFactory.getService(object.getType());
        id = service.save(object).getId();

        return id;
    }

    public Collateral getInfo(String type, Long id) {
        Collateral collateral;

        CrudService<Collateral, ?, Long> service = crudServiceFactory.getService(type);
        collateral = service.load(id);

        return collateral;
    }

    @Transactional
    public boolean addValue(Collateral object) {
        boolean result;

        Set<AssessedValue> assessedValues = object.getAssessedValues();

        CrudService<Collateral, ?, Long> service = crudServiceFactory.getService(object.getType());

        result = service.load(object.getId()).getAssessedValues().addAll(assessedValues);

        return result;
    }

    public List<Collateral> findAllAs(Collateral collateral) {
        CrudService<Collateral, ?, Long> service = crudServiceFactory.getService(collateral.getType());
        return service.findAllAsPresent(collateral);
    }
}
