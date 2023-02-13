package com.administration.service;

import com.administration.dto.ProfileRequestDTO;
import com.administration.dto.ProfileResponseDTO;
import com.administration.dto.ProfileUpdateDTO;
import com.administration.mappers.ProfileMapper;
import com.administration.repo.ProfileRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService{
    ProfileRepo profileRepo;
    ProfileMapper profileMapper;

    public ProfileServiceImpl(ProfileRepo profileRepo, ProfileMapper profileMapper) {
        this.profileRepo = profileRepo;
        this.profileMapper = profileMapper;
    }

    @Override
    public ProfileResponseDTO addProfile(ProfileRequestDTO RequestDTO) {
        
        return null;
    }

    @Override
    public ProfileResponseDTO getProfile(String id) {
        return null;
    }

    @Override
    public List<ProfileResponseDTO> listProfiles() {
        return null;
    }

    @Override
    public void updateProfileDTO(ProfileUpdateDTO dto) {

    }
}
