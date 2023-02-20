package com.administration.service;

import com.administration.dto.ProfileRequestDTO;
import com.administration.dto.ProfileResponseDTO;
import com.administration.dto.ProfileUpdateDTO;

import java.util.List;

public interface ProfileService {
    ProfileResponseDTO addProfile(ProfileRequestDTO RequestDTO);
    ProfileResponseDTO getProfile(String id);
    List<ProfileResponseDTO> listProfiles();
    void updateProfileDTO(ProfileUpdateDTO dto);
    void affecterFoncToProfile(String idFonc,String idProfile);
    void affecterModelToProfile(String idModel,String idProfile);
    void removeFonc(String idFonc, String idProfile);

    void removeModel(String idProfile)

    void deleteProfile(String idProfile);
}
