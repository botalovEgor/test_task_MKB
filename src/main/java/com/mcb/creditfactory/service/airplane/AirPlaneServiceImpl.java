package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.AirPlane;
import com.mcb.creditfactory.repository.AirPlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirPlaneServiceImpl implements AirPlaneService {

    @Autowired
    private ExternalApproveService approveService;

    @Autowired
    private AirPlaneRepository airPlaneRepository;

    @Override
    public AirPlaneDto save(AirPlaneDto airPlane) {
        return toDTO(airPlaneRepository.save(fromDto(airPlane)));
    }

    @Override
    public AirPlaneDto load(Long id) {
        AirPlane airPlane = airPlaneRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Not found"));
        return toDTO(airPlane);
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
