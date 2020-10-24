package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.model.AirPlane;
import com.mcb.creditfactory.repository.AirPlaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirPlaneServiceImpl implements AirPlaneService {

    private final AirPlaneRepository airPlaneRepository;

    @Override
    public AirPlaneDto save(AirPlaneDto airPlane) {
        return toDTO(airPlaneRepository.save(fromDto(airPlane)));
    }

    @Override
    public AirPlaneDto load(Long id) {
        AirPlane airPlane = airPlaneRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Not found"));
        return toDTO(airPlane);
    }

    public List<AirPlaneDto> findAllAsPresent(AirPlaneDto airPlaneDto){
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<AirPlane> exampleQuery = Example.of(fromDto(airPlaneDto), matcher);
        List<AirPlane> airPlanes = airPlaneRepository.findAll(exampleQuery);
        return airPlanes.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public AirPlane fromDto(AirPlaneDto dto) {
        return new AirPlane(
                dto.getId(),
                dto.getBrand(),
                dto.getModel(),
                dto.getManufacturer(),
                dto.getYear(),
                dto.getFuelCapacity(),
                dto.getSeats(),
                dto.getAssessedValues()
        );
    }

    @Override
    public AirPlaneDto toDTO(AirPlane airPlane) {

        return new AirPlaneDto(
                airPlane.getId(),
                airPlane.getBrand(),
                airPlane.getModel(),
                airPlane.getManufacturer(),
                airPlane.getYear(),
                airPlane.getFuelCapacity(),
                airPlane.getSeats(),
                "airPlane",
                airPlane.getAssessedValues()
            );
    }

}
