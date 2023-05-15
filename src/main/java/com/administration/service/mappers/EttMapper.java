package com.administration.service.mappers;

import com.administration.dto.EttRequestDTO;
import com.administration.dto.EttResponseDTO;
import com.administration.dto.EttUpdateDTO;
import com.administration.entity.Ett;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EttMapper {
    EttResponseDTO EttTOEttResponseDTO(Ett ett);
    Ett EttRequestDTOEtt(EttRequestDTO ettRequestDTOR);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEttFromDto(EttUpdateDTO dto, @MappingTarget Ett entity);
}
