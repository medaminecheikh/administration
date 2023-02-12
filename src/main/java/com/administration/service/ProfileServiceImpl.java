package com.administration.service;

import com.administration.dto.ProfileRequestDTO;
import com.administration.dto.ProfileResponseDTO;
import com.administration.dto.ProfileUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService{
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
