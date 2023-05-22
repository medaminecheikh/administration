package com.administration.service;

import com.administration.dto.ProfilRequestDTO;
import com.administration.dto.ProfilResponseDTO;
import com.administration.dto.ProfilUpdateDTO;
import com.administration.entity.Profil;

import java.util.List;

public interface IProfilService {
    ProfilResponseDTO addProfile(ProfilRequestDTO RequestDTO);
    ProfilResponseDTO getProfile(String id);
    List<ProfilResponseDTO> listProfiles();
    void updateProfileDTO(ProfilUpdateDTO dto);
    void affecterFoncToProfile(String idFonc,String idProfile);
    void affecterModelToProfile(String idModel,String idProfile);
    void removeFonc(String idFonc, String idProfile);
    void removeModel(String idProfile);
    void deleteProfile(String idProfile);
    Profil getProfilbyName(String nomp);
    List<ProfilResponseDTO> findProfilsBynames(String kw, String desc, int page, int size);

}
