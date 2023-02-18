package com.administration.mappers;

import com.administration.dto.ProfileRequestDTO;
import com.administration.dto.ProfileResponseDTO;
import com.administration.dto.ProfileUpdateDTO;
import com.administration.entity.Profile;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileResponseDTO ProfileTOProfileResponseDTO(Profile profile);
    Profile ProfileRequestDTOProfile(ProfileRequestDTO profileRequestDTO);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfileFromDto(ProfileUpdateDTO dto, @MappingTarget Profile entity);
}
