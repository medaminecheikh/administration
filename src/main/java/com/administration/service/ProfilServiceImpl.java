package com.administration.service;

import com.administration.dto.ProfilRequestDTO;
import com.administration.dto.ProfilResponseDTO;
import com.administration.dto.ProfilUpdateDTO;
import com.administration.entity.*;
import com.administration.mappers.ProfilMapper;
import com.administration.repo.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;



@Service
@Slf4j
public class ProfilServiceImpl implements ProfilService {
    ProfileRepo profileRepo;
    ProfilMapper profilMapper;
    FoncRepo foncRepo;
    ModelRepo modelRepo;
    UtilisateurRepo utilisateurRepo;

    EttRepo ettRepo;

    public ProfilServiceImpl(ProfileRepo profileRepo, ProfilMapper profilMapper, FoncRepo foncRepo, ModelRepo modelRepo, UtilisateurRepo utilisateurRepo, EttRepo ettRepo) {
        this.profileRepo = profileRepo;
        this.profilMapper = profilMapper;
        this.foncRepo = foncRepo;
        this.modelRepo = modelRepo;
        this.utilisateurRepo = utilisateurRepo;
        this.ettRepo = ettRepo;
    }

    @Override
    public ProfilResponseDTO addProfile(ProfilRequestDTO RequestDTO) {
        Profil profil = profilMapper.ProfileRequestDTOProfile(RequestDTO);
        profil.setIdProfil(UUID.randomUUID().toString());
        Profil profilesave=profileRepo.save(profil);
        ProfilResponseDTO profilResponseDTO = profilMapper.ProfileTOProfileResponseDTO(profilesave);
        return profilResponseDTO;
    }

    @Override
    public ProfilResponseDTO getProfile(String id) {
        Profil profil = profileRepo.findById(id).get();
        ProfilResponseDTO profilResponseDTO = profilMapper.ProfileTOProfileResponseDTO(profil);
        return profilResponseDTO;
    }

    @Override
    public List<ProfilResponseDTO> listProfiles() {
        List<Profil> profils =profileRepo.findAll();

        List<ProfilResponseDTO> profilResponseDTOList = profils.stream()
                .map(profile -> profilMapper.ProfileTOProfileResponseDTO(profile))
                .collect(Collectors.toList());
        return profilResponseDTOList;
    }

    @Override
    public void updateProfileDTO(ProfilUpdateDTO dto) {
        Profil profil = profileRepo.findById(dto.getIdProfil()).get();
        profilMapper.updateProfileFromDto(dto, profil);
        profileRepo.save(profil);

    }

    @Override
    public void affecterFoncToProfile(String idFonc, String idProfile) {
        Profil profil =profileRepo.findById(idProfile).get();
        Fonction fonction =foncRepo.findById(idFonc).get();

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
        Fonction fonction1 =foncRepo.findById(idFonc).get();
        Profil profil =profileRepo.findById(idProfile).get();
        profil.getFonctions().remove(fonction1);
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
        if (profil.getProfileUsers().isEmpty()&& profil.getFonctions().isEmpty()&& profil.getModel()==null)
        {
            profileRepo.deleteById(idProfile);
        }else  throw new RuntimeException("This profile has associations !!");
    }


}
