package com.mcb.creditfactory.service;

import java.util.List;

public interface CrudService<DTO, ENTITY, ID> {
    DTO save(DTO dto);
    DTO load(ID id);
    ENTITY fromDto(DTO dto);
    DTO toDTO(ENTITY entity);
    List<DTO> findAllAsPresent(DTO dto);
}
