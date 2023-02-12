package com.administration.mappers;

import com.administration.dto.FoncRequestDTO;
import com.administration.dto.FoncResponseDTO;
import com.administration.dto.FoncUpdateDTO;
import com.administration.entity.Fonctionalite;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface FoncMapper {
    FoncResponseDTO FonctionaliteTOFonctionaliteResponseDTO(Fonctionalite fonctionalite);
    Fonctionalite FonctionaliteRequestDTOFonctionalite(FoncRequestDTO foncRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFonctionaliteFromDto(FoncUpdateDTO dto, @MappingTarget Fonctionalite entity);

}
