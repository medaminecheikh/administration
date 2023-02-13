package com.administration.service;

import com.administration.dto.ProfileRequestDTO;
import com.administration.dto.ProfileResponseDTO;
import com.administration.dto.ProfileUpdateDTO;
import com.administration.entity.Fonctionalite;
import com.administration.entity.Profile;
import com.administration.mappers.ProfileMapper;
import com.administration.repo.FoncRepo;
import com.administration.repo.ProfileRepo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;



@Service
@Slf4j
public class ProfileServiceImpl implements ProfileService{
    ProfileRepo profileRepo;
    ProfileMapper profileMapper;
    FoncRepo foncRepo;


    public ProfileServiceImpl(ProfileRepo profileRepo, ProfileMapper profileMapper, FoncRepo foncRepo) {
        this.profileRepo = profileRepo;
        this.profileMapper = profileMapper;
        this.foncRepo = foncRepo;
    }

    @Override
    public ProfileResponseDTO addProfile(ProfileRequestDTO RequestDTO) {
        Profile profile=profileMapper.ProfileRequestDTOProfile(RequestDTO);
        profile.setIdProfile(UUID.randomUUID().toString());
        Profile profilesave=profileRepo.save(profile);
        ProfileResponseDTO profileResponseDTO=profileMapper.ProfileTOProfileResponseDTO(profilesave);
        return profileResponseDTO;
    }

    @Override
    public ProfileResponseDTO getProfile(String id) {
        Profile profile= profileRepo.findById(id).get();
        ProfileResponseDTO profileResponseDTO=profileMapper.ProfileTOProfileResponseDTO(profile);
        return profileResponseDTO;
    }

    @Override
    public List<ProfileResponseDTO> listProfiles() {
        List<Profile> profiles=profileRepo.findAll();
    
        List<ProfileResponseDTO> profileResponseDTOList= profiles.stream()
                .map(profile -> profileMapper.ProfileTOProfileResponseDTO(profile))
                .collect(Collectors.toList());
        return profileResponseDTOList;
    }

    @Override
    public void updateProfileDTO(ProfileUpdateDTO dto) {
        Profile profile= profileRepo.findById(dto.getIdProfile()).get();
        profileMapper.updateProfileFromDto(dto,profile);
        profileRepo.save(profile);

    }

    @Override
    public void affecterFoncToProfile(String idFonc, String idProfile) {
        Profile profile=profileRepo.findById(idProfile).get();
        Fonctionalite fonctionalite=foncRepo.findById(idFonc).get();

        profile.getFunctionalites().add(fonctionalite);
        profileRepo.save(profile);
    }

}
