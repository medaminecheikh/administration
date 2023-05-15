package com.administration.service.mappers;

import com.administration.dto.CaisseRequestDTO;
import com.administration.dto.CaisseResponseDTO;
import com.administration.dto.CaisseUpdateDTO;
import com.administration.entity.Caisse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CaisseMappers {
    CaisseResponseDTO CaisseTOCaisseResponseDTO(Caisse caisse);
    Caisse CaisseRequestDTOCaisse(CaisseRequestDTO caisseRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCaisseFromDto(CaisseUpdateDTO dto, @MappingTarget Caisse entity);
}
