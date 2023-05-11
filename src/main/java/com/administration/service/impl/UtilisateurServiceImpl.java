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
import com.administration.repo.ProfilUserRepo;
import com.administration.repo.ProfileRepo;
import com.administration.repo.UtilisateurRepo;
import com.administration.service.IUtilisateurService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Iterator;
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
    ProfilUserRepo profilUserRepo;

    @Override
    public UtilisateurResponseDTO addUtilisateur(UtilisateurRequestDTO RequestDTO) {
        String login=RequestDTO.getLogin().toLowerCase();
        Utilisateur userexist=utilisateurRepo.findByLogin(login);
        if (userexist!=null) {
            throw new IllegalArgumentException("Login with the name " + login + " already exists.");
        }
        if (!RequestDTO.getPwdU().equals(RequestDTO.getConfirmedpassword())){
            throw new RuntimeException("Please confirm your password !!!!!!!");
        }
        Utilisateur utilisateur=userMapper.UtilisateurRequestDTOUtilisateur(RequestDTO);
        utilisateur.setIdUser(UUID.randomUUID().toString());
        utilisateur.setLogin(utilisateur.getLogin().toLowerCase());
        utilisateur.setPwdU(bCryptPasswordEncoder.encode(utilisateur.getPwdU()));
        utilisateur.setDate_CREATION(new Date());
        utilisateurRepo.save(utilisateur);
        return userMapper.UtilisateurTOUtilisateurResponseDTO(utilisateur);
    }

    @Override
    public UtilisateurResponseDTO getUtilisateur(String id) {
        Utilisateur utilisateur =utilisateurRepo.findById(id).get();
        return userMapper.UtilisateurTOUtilisateurResponseDTO(utilisateur);
    }
    @Override
    public Utilisateur getUtilisateurbyLogin(String username) {

        return utilisateurRepo.findByLogin(username);
    }

    @Override
    public UtilisateurResponseDTO getbyLogin(String username) {
       Utilisateur utilisateur= utilisateurRepo.findByLogin(username);
        return userMapper.UtilisateurTOUtilisateurResponseDTO(utilisateur);
    }

    @Override
    public List<UtilisateurResponseDTO> listUtilisateurs() {
        List<Utilisateur> utilisateurs=utilisateurRepo.findAll();
        return utilisateurs.stream()
                .map(utilisateur -> userMapper.UtilisateurTOUtilisateurResponseDTO(utilisateur))
                .collect(Collectors.toList());
    }

    @Override
    public void updateUtilisateurDTO(UtilisateurUpdateDTO dto) {
            Utilisateur utilisateur=utilisateurRepo.findById(dto.getIdUser()).get();
        if (dto.getPwdU() != null){
            dto.setPwdU(bCryptPasswordEncoder.encode(dto.getPwdU()));
        }
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
        Utilisateur utilisateur = utilisateurRepo.findById(idUser).get();
        Profil profil = profileRepo.findById(idProfile).get();

        boolean profileExists = false;
        for (ProfilUser profilUser : utilisateur.getProfilUser()) {
            if (profilUser.getProfil().equals(profil)) {
                profileExists = true;
                break;
            }
        }

        if (!profileExists) {
            ProfilUser profilUser = new ProfilUser();
            profilUser.setProfil(profil);
            profilUser.setUtilisateur(utilisateur);
            utilisateur.getProfilUser().add(profilUser);
            utilisateurRepo.save(utilisateur);
        } else {
            throw new RuntimeException("This profile already exists for the user");
        }
    }


    @Override
    public void removeEtt(String idUser) {
        Utilisateur utilisateur=utilisateurRepo.findById(idUser).get();
        utilisateur.setEtt(null);
        utilisateurRepo.save(utilisateur);
    }

    @Override
    public void removeProfile(String userId, String profilId) {
        Utilisateur utilisateur = utilisateurRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id"));
        Profil profilToRemove = profileRepo.findById(profilId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid profil id"));

        List<ProfilUser> profilUsers = utilisateur.getProfilUser();
        if (profilUsers != null) {
            Iterator<ProfilUser> iterator = profilUsers.iterator();
            while(iterator.hasNext()) {
                ProfilUser profilUser = iterator.next();
                if (profilUser.getProfil().getIdProfil().equals(profilId)) {
                    profilUser.setUtilisateur(null);
                    profilUser.setProfil(null);
                    profilUserRepo.deleteById(profilUser.getId());
                    iterator.remove();
                }
            }
            utilisateur.setProfilUser(profilUsers);
            utilisateurRepo.save(utilisateur);
        }
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
        Sort sort = Sort.by("idUser");
        Page<Utilisateur> utilisateurs=utilisateurRepo.findUtilisateurByLogin(kw, PageRequest.of(page, size,sort));
        List<UtilisateurResponseDTO> utilisateurResponseDTOList=utilisateurs
                .map(utilisateur -> userMapper.UtilisateurTOUtilisateurResponseDTO(utilisateur))
                .getContent();
        long count=utilisateurs.getTotalElements();
        utilisateurResponseDTOList.forEach(dto -> dto.setTotalElements(count));

        return utilisateurResponseDTOList;
    }
}
