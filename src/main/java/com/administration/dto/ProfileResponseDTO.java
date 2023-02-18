package com.administration.dto;

import com.administration.entity.Utilisateur;
import lombok.Data;

import java.util.List;

@Data
public class ProfileResponseDTO {
    private String idProfile;
    private String nomProfile;
    private ModelUpdateDTO model;
    private List<FoncUpdateDTO> functionalites;
    private List<ProfileUserViewProfileDTO> profileUsers;
}
