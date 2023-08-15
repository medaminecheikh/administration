package com.administration.service.mappers;

import com.administration.dto.FactureRequestDTO;
import com.administration.dto.FactureResponseDTO;
import com.administration.dto.FactureUpdateDTO;
import com.administration.entity.InfoFacture;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface FactureMapper {
    FactureResponseDTO FactureTOFactureResponseDTO(InfoFacture facture);
    InfoFacture FactureRequestDTOFacture(FactureRequestDTO factureRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFactureFromDto(FactureUpdateDTO dto, @MappingTarget InfoFacture entity);
}
