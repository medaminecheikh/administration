package com.administration.Interface;

import com.administration.dto.UtilisateurRequestDTO;
import com.administration.dto.UtilisateurResponseDTO;
import com.administration.dto.UtilisateurUpdateDTO;
import com.administration.entity.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUtilisateurService {

    UtilisateurResponseDTO addUtilisateur(UtilisateurRequestDTO RequestDTO);
    UtilisateurResponseDTO getUtilisateur(String id);
    List<UtilisateurResponseDTO> listUtilisateurs();
    void updateUtilisateurDTO(UtilisateurUpdateDTO dto);
    void affecterUserToEtt(String idUser,String idEtt);
    void affecterProfileToUser(String idUser,String idProfile);

    void removeEtt(String idUser);

    void removeProfile(String idUser);

    void deleteUser(String idUser);

    List<UtilisateurResponseDTO> findUtilisateurByLogin(String kw, int page, int size);
}
