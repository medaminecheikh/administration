package com.administration.service.mappers;

import com.administration.dto.ProfilResponseDTO;
import com.administration.dto.TracageResponse;
import com.administration.entity.Profil;
import com.administration.entity.Tracage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TracageMapper {
    TracageResponse TracageTOTracageResponseDTO(Tracage tracage);
}
