package com.administration.service.mappers;

import com.administration.dto.UtilisateurRequestDTO;
import com.administration.dto.UtilisateurResponseDTO;
import com.administration.dto.UtilisateurUpdateDTO;
import com.administration.entity.Utilisateur;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
@Mapper(componentModel = "spring")
public interface UserMapper {
    UtilisateurResponseDTO UtilisateurTOUtilisateurResponseDTO(Utilisateur utilisateur);
    Utilisateur UtilisateurRequestDTOUtilisateur(UtilisateurRequestDTO utilisateurRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUtilisateurFromDto(UtilisateurUpdateDTO dto, @MappingTarget Utilisateur entity);
}
