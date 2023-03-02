package com.administration.service.impl;

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
import com.administration.service.IUtilisateurService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class UtilisateurServiceImpl implements IUtilisateurService {
    UtilisateurRepo utilisateurRepo;
    UserMapper userMapper;
    ProfileRepo profileRepo;
    EttRepo ettRepo;
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UtilisateurResponseDTO addUtilisateur(UtilisateurRequestDTO RequestDTO) {
        String login=RequestDTO.getLogin().toLowerCase();
        Utilisateur userexist=utilisateurRepo.findByLogin(login);
        if (userexist!=null) {
            throw new IllegalArgumentException("Login with the name " + login + " already exists.");
        }
        if (RequestDTO.getPwdU()!=RequestDTO.getConfirmedpassword()){
            throw new RuntimeException("Please confirm your password");
        }
        Utilisateur utilisateur=userMapper.UtilisateurRequestDTOUtilisateur(RequestDTO);
        utilisateur.setIdUser(UUID.randomUUID().toString());
        utilisateur.setLogin(utilisateur.getLogin().toLowerCase());
        utilisateur.setPwdU(bCryptPasswordEncoder.encode(utilisateur.getPwdU()));
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
    public Utilisateur getUtilisateurbyLogin(String username) {
        Utilisateur utilisateur =utilisateurRepo.findByLogin(username);

        return utilisateur;
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
        utilisateur.getProfilUser().add(profilUser);
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

    @Override
    public List<UtilisateurResponseDTO> findUtilisateurByLogin(String kw, int page, int size) {
        Page<Utilisateur> utilisateurs=utilisateurRepo.findUtilisateurByLogin(kw, PageRequest.of(page, size));
        long count =utilisateurRepo.count();
        List<UtilisateurResponseDTO> utilisateurResponseDTOList=utilisateurs.getContent().stream()
                .map(utilisateur -> userMapper.UtilisateurTOUtilisateurResponseDTO(utilisateur))
                .collect(Collectors.toList());
        for (UtilisateurResponseDTO responseDTO : utilisateurResponseDTOList
        ){
            responseDTO.setTotalElements(count);
        }

        return utilisateurResponseDTOList;
    }
}
