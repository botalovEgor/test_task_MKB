package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.external.CollateralType;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.AirPlane;
import com.mcb.creditfactory.model.AssessedValue;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.repository.AirPlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AirPlaneServiceImpl implements AirPlaneService {

    @Autowired
    private AirPlaneRepository airPlaneRepository;

    @Override
    public AirPlaneDto save(AirPlaneDto airPlaneDto) {
        return toDTO(airPlaneRepository.save(fromDto(airPlaneDto)));
    }

    @Override
    public AirPlaneDto load(Long id) {
        AirPlane airPlane =  airPlaneRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(String.format("AirPlane with id %d not found", id)));

        return toDTO(airPlane);
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
        assessedValue.setValue(dto.getValue());

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
                last.getId(),
                last.getValue(),
                last.getEvaluationDate(),
                CollateralType.AIRPLANE
        );
    }

    @Override
    public Long getId(AirPlane airPlane) {
        return airPlane.getId();
    }

    private AssessedValue getLastAssessedValue(AirPlane airPlane) {
        return airPlane.getAssessedValues().stream().max(Comparator.comparing(AssessedValue::getEvaluationDate)).get();
    }

    @Override
    public Collateral addValue(AirPlaneDto airPlaneDto) {
        AssessedValue newAssessedValue = new AssessedValue();
        newAssessedValue.setEvaluationDate(airPlaneDto.getDate());
        newAssessedValue.setValue(airPlaneDto.getValue());

        AirPlane airPlane = airPlaneRepository.findById(airPlaneDto.getId())
                .orElseThrow(()-> new IllegalArgumentException("AirPlane not found"));
        airPlane.getAssessedValues().add(newAssessedValue);
        airPlaneDto.setValueId(getLastAssessedValue(airPlane).getId());
        return airPlaneDto;
    }
}