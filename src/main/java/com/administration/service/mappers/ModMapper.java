package com.administration.service.mappers;

import com.administration.dto.ModelRequestDTO;
import com.administration.dto.ModelResponseDTO;
import com.administration.dto.ModelUpdateDTO;
import com.administration.entity.Model;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ModMapper {
    ModelResponseDTO ModelTOModelResponseDTO(Model model);
    Model ModelRequestDTOModel(ModelRequestDTO modelRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromDto(ModelUpdateDTO dto, @MappingTarget Model entity);
}
