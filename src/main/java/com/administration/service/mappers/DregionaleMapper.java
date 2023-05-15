package com.administration.service.mappers;

import com.administration.dto.DregionalRequestDTO;
import com.administration.dto.DregionalResponseDTO;
import com.administration.dto.DregionalUpdateDTO;
import com.administration.entity.Dregional;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface DregionaleMapper {
    DregionalResponseDTO DregionaleTODregionaleResponseDTO(Dregional dregional);
    Dregional DregionaleRequestDTODregionale(DregionalRequestDTO dregionalRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDregionaleFromDto(DregionalUpdateDTO dto, @MappingTarget Dregional entity);
}
