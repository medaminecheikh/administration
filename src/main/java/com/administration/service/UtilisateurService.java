package com.administration.service;

import com.administration.dto.UtilisateurRequestDTO;
import com.administration.dto.UtilisateurResponseDTO;
import com.administration.dto.UtilisateurUpdateDTO;

import java.util.List;

public interface UtilisateurService {

    UtilisateurResponseDTO addUtilisateur(UtilisateurRequestDTO RequestDTO);
    UtilisateurResponseDTO getUtilisateur(String id);
    List<UtilisateurResponseDTO> listUtilisateurs();
    void updateUtilisateurDTO(UtilisateurUpdateDTO dto);

    void affecterProfileToUser(String idUser,String idProfile);
}
