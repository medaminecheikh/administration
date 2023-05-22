package com.administration.service.impl;

import com.administration.dto.ProfilRequestDTO;
import com.administration.dto.ProfilResponseDTO;
import com.administration.dto.ProfilUpdateDTO;
import com.administration.entity.*;
import com.administration.service.mappers.ProfilMapper;
import com.administration.repo.*;

import com.administration.service.IProfilService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;



@Service
@Slf4j@Transactional
@AllArgsConstructor

public class ProfilServiceImpl implements IProfilService {
    ProfileRepo profileRepo;
    ProfilMapper profilMapper;
    FoncRepo foncRepo;
    ModelRepo modelRepo;
    UtilisateurRepo utilisateurRepo;

    EttRepo ettRepo;



    @Override
    public ProfilResponseDTO addProfile(ProfilRequestDTO RequestDTO) {
        String profileName = RequestDTO.getNomP().toUpperCase();
        Profil existingProfileOptional = profileRepo.findByNomP(profileName);
        if (existingProfileOptional!=null) {
            throw new IllegalArgumentException("Profile with the name " + profileName + " already exists.");
        }else {
        Profil profil = profilMapper.ProfileRequestDTOProfile(RequestDTO);
        profil.setIdProfil(UUID.randomUUID().toString());
        profil.setNomP(profil.getNomP().toUpperCase());
        Profil profilesave=profileRepo.save(profil);
            return profilMapper.ProfileTOProfileResponseDTO(profilesave);}
    }

    @Override
    public ProfilResponseDTO getProfile(String id) {
        Profil profil = profileRepo.findById(id).get();
        return profilMapper.ProfileTOProfileResponseDTO(profil);
    }

    @Override
    public List<ProfilResponseDTO> listProfiles() {
        List<Profil> profils =profileRepo.findAll();

        return profils.stream()
                .map(profile -> profilMapper.ProfileTOProfileResponseDTO(profile))
                .collect(Collectors.toList());
    }

    @Override
    public void updateProfileDTO(ProfilUpdateDTO dto) {
        Profil profil = profileRepo.findById(dto.getIdProfil()).get();
        profilMapper.updateProfileFromDto(dto, profil);
        profileRepo.save(profil);

    }

    @Override
    public void affecterFoncToProfile(String idFonc, String idProfile) {
        Profil profil = profileRepo.findById(idProfile).orElseThrow(() -> new RuntimeException("Profile not found"));
        Fonction fonction = foncRepo.findById(idFonc).orElseThrow(() -> new RuntimeException("Function not found"));

        profil.getFonctions().add(fonction);
        profileRepo.save(profil);
    }



    @Override
    public void affecterModelToProfile(String idModel, String idProfile) {
        Profil profil =profileRepo.findById(idProfile).get();
        Model model=modelRepo.findById(idModel).get();
        profil.setModel(model);
        profileRepo.save(profil);

    }

    @Override
    public void removeFonc(String idFonc, String idProfile) {
        Fonction fonction = foncRepo.findById(idFonc).orElseThrow(() -> new RuntimeException("Function not found"));
        Profil profil = profileRepo.findById(idProfile).orElseThrow(() -> new RuntimeException("Profile not found"));

        profil.getFonctions().remove(fonction);
        profileRepo.save(profil);

    }

    @Override
    public void removeModel(String idProfile) {
        Profil profil =profileRepo.findById(idProfile).get();
        profil.setModel(null);
        profileRepo.save(profil);


    }

    @Override
    public void deleteProfile(String idProfile) {
        Profil profil =profileRepo.findById(idProfile).get();
        if (profil.getProfilUsers().isEmpty()&& profil.getFonctions().isEmpty()&& profil.getModel()==null)
        {
            profileRepo.deleteById(idProfile);
        }else  throw new RuntimeException("This profile has associations !!");
    }

    @Override
    public Profil getProfilbyName(String nomp) {

        return profileRepo.findByNomP(nomp.toUpperCase());
    }

    @Override
    public List<ProfilResponseDTO> findProfilsBynames(String kw, String desc, int page, int size) {
        Sort sort = Sort.by("idProfil");
        Page<Profil> profilPage =profileRepo.findProfilsByNomP(kw,desc, PageRequest.of(page, size,sort));
        List<ProfilResponseDTO> profilResponseDTOList =profilPage
                .map(profil -> profilMapper.ProfileTOProfileResponseDTO(profil))
                .getContent();
        long count =profilPage.getTotalElements();
        profilResponseDTOList.forEach(profil -> profil.setTotalElements(count));
        return profilResponseDTOList;
    }



}
