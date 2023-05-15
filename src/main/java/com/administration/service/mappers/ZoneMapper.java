package com.administration.service.mappers;

import com.administration.dto.ZoneRequestDTO;
import com.administration.dto.ZoneResponseDTO;
import com.administration.dto.ZoneUpdateDTO;
import com.administration.entity.Zone;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ZoneMapper {
    ZoneResponseDTO ZoneTOZoneResponseDTO(Zone zone);
    Zone ZoneRequestDTOZone(ZoneRequestDTO zoneRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateZoneFromDto(ZoneUpdateDTO dto, @MappingTarget Zone entity);
}
