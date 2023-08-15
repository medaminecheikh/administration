package com.administration.service.mappers;

import com.administration.dto.EncaissRequestDTO;
import com.administration.dto.EncaissResponseDTO;
import com.administration.dto.EncaissUpdateDTO;
import com.administration.entity.Encaissement;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EncaissMapper {
    EncaissResponseDTO EncaissTOEncaissResponseDTO(Encaissement Encaiss);
    Encaissement EncaissRequestDTOEncaiss(EncaissRequestDTO EncaissRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEncaissFromDto(EncaissUpdateDTO dto, @MappingTarget Encaissement entity);
}
