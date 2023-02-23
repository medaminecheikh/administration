package com.administration.service;

import com.administration.Interface.IUtilisateurService;
import com.administration.dto.UtilisateurRequestDTO;
import com.administration.dto.UtilisateurResponseDTO;
import com.administration.dto.UtilisateurUpdateDTO;
import com.administration.entity.Ett;
import com.administration.entity.Profil;
import com.administration.entity.ProfilUser;
import com.administration.entity.Utilisateur;
import com.administration.mappers.UserMapper;
import com.administration.repo.EttRepo;
import com.administration.repo.ProfileRepo;
import com.administration.repo.UtilisateurRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UtilisateurServiceImpl implements IUtilisateurService {
    UtilisateurRepo utilisateurRepo;
    UserMapper userMapper;
    ProfileRepo profileRepo;
    EttRepo ettRepo;

    public UtilisateurServiceImpl(UtilisateurRepo utilisateurRepo, UserMapper userMapper, ProfileRepo profileRepo, EttRepo ettRepo) {
        this.utilisateurRepo = utilisateurRepo;
        this.userMapper = userMapper;
        this.profileRepo = profileRepo;
        this.ettRepo = ettRepo;
    }

    @Override
    public UtilisateurResponseDTO addUtilisateur(UtilisateurRequestDTO RequestDTO) {
        Utilisateur utilisateur=userMapper.UtilisateurRequestDTOUtilisateur(RequestDTO);
        utilisateur.setIdUser(UUID.randomUUID().toString());
        utilisateurRepo.save(utilisateur);
        UtilisateurResponseDTO utilisateurResponseDTO=userMapper.UtilisateurTOUtilisateurResponseDTO(utilisateur);
        return utilisateurResponseDTO;
    }

    @Override
    public UtilisateurResponseDTO getUtilisateur(String id) {
        Utilisateur utilisateur =utilisateurRepo.findById(id).get();
        UtilisateurResponseDTO utilisateurResponseDTO=userMapper.UtilisateurTOUtilisateurResponseDTO(utilisateur);
        return utilisateurResponseDTO;
    }

    @Override
    public List<UtilisateurResponseDTO> listUtilisateurs() {
        List<Utilisateur> utilisateurs=utilisateurRepo.findAll();
        List<UtilisateurResponseDTO> utilisateurResponseDTOList=utilisateurs.stream()
                .map(utilisateur -> userMapper.UtilisateurTOUtilisateurResponseDTO(utilisateur))
                .collect(Collectors.toList());
        return utilisateurResponseDTOList;
    }

    @Override
    public void updateUtilisateurDTO(UtilisateurUpdateDTO dto) {
            Utilisateur utilisateur=utilisateurRepo.findById(dto.getIdUser()).get();
            userMapper.updateUtilisateurFromDto(dto,utilisateur);
            utilisateurRepo.save(utilisateur);
    }

    @Override
    public void affecterUserToEtt(String idUser, String idEtt) {
        Utilisateur utilisateur=utilisateurRepo.findById(idUser).get();
        Ett ett=ettRepo.findById(idEtt).get();
        utilisateur.setEtt(ett);
        utilisateurRepo.save(utilisateur);
    }

    @Override
    public void affecterProfileToUser(String idUser, String idProfile) {
        Utilisateur utilisateur=utilisateurRepo.findById(idUser).get();
        Profil profil =profileRepo.findById(idProfile).get();
        ProfilUser profilUser =new ProfilUser();
        profilUser.setProfil(profil);
        profilUser.setUtilisateur(utilisateur);
        utilisateur.setProfilUser(profilUser);
        utilisateurRepo.save(utilisateur);
    }

    @Override
    public void removeEtt(String idUser) {
        Utilisateur utilisateur=utilisateurRepo.findById(idUser).get();
        utilisateur.setEtt(null);
        utilisateurRepo.save(utilisateur);
    }

    @Override
    public void removeProfile(String idUser) {
        Utilisateur utilisateur=utilisateurRepo.findById(idUser).get();
        utilisateur.setProfilUser(null);
        utilisateurRepo.save(utilisateur);
    }

    @Override
    public void deleteUser(String idUser) {
        Utilisateur utilisateur=utilisateurRepo.findById(idUser).get();
        if (utilisateur.getEtt()==null&&utilisateur.getProfilUser()==null)
        {
            utilisateurRepo.deleteById(idUser);
        }else throw new RuntimeException("This user "+utilisateur.getNomU()+" has associations");
    }
}
