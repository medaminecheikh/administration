package com.administration.service.mappers;

import com.administration.dto.FoncRequestDTO;
import com.administration.dto.FoncResponseDTO;
import com.administration.dto.FoncUpdateDTO;
import com.administration.entity.Fonction;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface FoncMapper {
    FoncResponseDTO FonctionaliteTOFonctionaliteResponseDTO(Fonction fonction);
    Fonction FonctionaliteRequestDTOFonctionalite(FoncRequestDTO foncRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFonctionaliteFromDto(FoncUpdateDTO dto, @MappingTarget Fonction entity);

}
