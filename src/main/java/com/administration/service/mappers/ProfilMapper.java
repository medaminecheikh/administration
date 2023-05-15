package com.administration.service.mappers;

import com.administration.dto.ProfilRequestDTO;
import com.administration.dto.ProfilResponseDTO;
import com.administration.dto.ProfilUpdateDTO;
import com.administration.entity.Profil;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProfilMapper {
    ProfilResponseDTO ProfileTOProfileResponseDTO(Profil profil);
    Profil ProfileRequestDTOProfile(ProfilRequestDTO profilRequestDTO);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfileFromDto(ProfilUpdateDTO dto, @MappingTarget Profil entity);
}
