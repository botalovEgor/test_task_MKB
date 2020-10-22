package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.AirPlane;
import com.mcb.creditfactory.model.AssessedValue;
import com.mcb.creditfactory.repository.AirPlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AirPlaneServiceImpl implements AirPlaneService {
    @Autowired
    private ExternalApproveService approveService;

    @Autowired
    private AirPlaneRepository airPlaneRepository;

    @Override
    public boolean approve(AirPlaneDto dto) {
        return approveService.approve(new AirPlaneAdapter(dto)) == 0;
    }

    @Override
    public AirPlane save(AirPlane airPlane) {
        return airPlaneRepository.save(airPlane);
    }

    @Override
    public Optional<AirPlane> load(Long id) {
        return airPlaneRepository.findById(id);
    }

    @Override
    public AirPlane fromDto(AirPlaneDto dto) {
        AirPlane airPlane = new AirPlane();
        airPlane.setId(dto.getId());
        airPlane.setBrand(dto.getBrand());
        airPlane.setModel(dto.getModel());
        airPlane.setManufacturer(dto.getManufacturer());
        airPlane.setYear(dto.getYear());
        airPlane.setFuelCapacity(dto.getFuelCapacity());
        airPlane.setSeats(dto.getSeats());


        AssessedValue assessedValue = new AssessedValue();
        assessedValue.setEvaluationDate(LocalDate.now());
        assessedValue.setValue(dto.getLastValue());

        Set<AssessedValue> assessedValues = new HashSet<>();
        assessedValues.add(assessedValue);

        airPlane.setAssessedValues(assessedValues);

        return airPlane;
    }

    @Override
    public AirPlaneDto toDTO(AirPlane airPlane) {
        AssessedValue last = getLastAssessedValue(airPlane);

        return new AirPlaneDto(
                airPlane.getId(),
                airPlane.getBrand(),
                airPlane.getModel(),
                airPlane.getManufacturer(),
                airPlane.getYear(),
                airPlane.getFuelCapacity(),
                airPlane.getSeats(),
                last.getValue(),
                last.getEvaluationDate()
        );
    }

    @Override
    public Long getId(AirPlane airPlane) {
        return airPlane.getId();
    }

    private AssessedValue getLastAssessedValue(AirPlane airPlane) {
        return airPlane.getAssessedValues().stream().max(Comparator.comparing(AssessedValue::getEvaluationDate)).get();
    }
}
