package com.administration.service;

import com.administration.dto.UtilisateurRequestDTO;
import com.administration.dto.UtilisateurResponseDTO;
import com.administration.dto.UtilisateurUpdateDTO;
import com.administration.entity.UserView;
import com.administration.entity.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUtilisateurService {

    UtilisateurResponseDTO addUtilisateur(UtilisateurRequestDTO RequestDTO);
    UtilisateurResponseDTO getUtilisateur(String id);
    List<UtilisateurResponseDTO> listUtilisateurs();
    void updateUtilisateurDTO(UtilisateurUpdateDTO dto);
    void affecterUserToEtt(String idUser,String idEtt);
    void affecterProfileToUser(String idUser,String idProfile);

    void removeEtt(String idUser);

    void removeProfile(String idUser,String idProfil);

    void deleteUser(String idUser);

    List<UtilisateurResponseDTO> findUtilisateurByLogin(String kw,String nom,String prenom,Integer estActif, int page, int size);
    Utilisateur getUtilisateurbyLogin(String username);
    UtilisateurResponseDTO getbyLogin(String username);

    List<UtilisateurResponseDTO> findUtilisateurExpired(String login, String prenU, String nomU, String matricule, Integer estActif, Integer isExpired, String zoneId,String drId,String ettId,String profilId, PageRequest pageable);

    List<UtilisateurResponseDTO> findUtilisateurValid(String login, String prenU, String nomU, String matricule, Integer estActif, Integer isExpired, String zoneId,String drId,String ettId,String profilId, PageRequest pageable);

    List<UtilisateurResponseDTO> findUtilisateurAll(String login, String prenU, String nomU, String matricule, Integer estActif, String zoneId,String drId,String ettId,String profilId,Integer is_EXPIRED, PageRequest pageable);

    List<UtilisateurResponseDTO> getUtilisateurbyZone(String zoneId);


    /*UserView userviewByLogin(String username);
    List<UserView> getallUserView();
    List<UserView> getUserViewByEtt(String ett);*/
}
