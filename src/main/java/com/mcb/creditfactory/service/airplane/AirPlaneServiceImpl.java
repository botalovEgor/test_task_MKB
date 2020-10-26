package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.dto.AirplaneDtoMapper;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.AirPlane;
import com.mcb.creditfactory.repository.AirPlaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirPlaneServiceImpl implements AirPlaneService {

    private final AirPlaneRepository airPlaneRepository;
    private final AirplaneDtoMapper mapper;
    private final ExternalApproveService externalApproveService;

    @Transactional
    @Override
    public Long save(AirPlaneDto airPlaneDto) {
        AirPlane airPlane = mapper.fromDto(airPlaneDto);
        int approve = externalApproveService.approve(airPlane);
        if (approve == 0) {
            airPlaneRepository.save(airPlane);
        }
        return airPlane.getId();
    }

    @Override
    public AirPlaneDto load(Long id) {
        AirPlane airPlane = airPlaneRepository.findById(id).orElseThrow(()->new IllegalArgumentException(String.format("AirPlane with id %d not found", id)));
        return mapper.toDto(airPlane);
    }

    public List<AirPlaneDto> findAllAsPresent(AirPlaneDto airPlaneDto) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<AirPlane> exampleQuery = Example.of(mapper.fromDto(airPlaneDto), matcher);
        List<AirPlane> airPlanes = airPlaneRepository.findAll(exampleQuery);
        return airPlanes.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public boolean addNewEstimation(AirPlaneDto airPlaneDto) {
        boolean result = false;

        Optional<AirPlane> airPlaneOptional = airPlaneRepository.findById(airPlaneDto.getId());
        if(airPlaneOptional.isPresent()){
            result = airPlaneOptional.get().getAssessedValues().addAll(airPlaneDto.getAssessedValues());
        }

        return result;
    }

}
