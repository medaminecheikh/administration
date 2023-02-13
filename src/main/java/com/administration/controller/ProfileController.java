package com.administration.controller;

import com.administration.dto.ProfileRequestDTO;
import com.administration.dto.ProfileResponseDTO;
import com.administration.dto.ProfileUpdateDTO;
import com.administration.service.ProfileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileController {
    
     ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @ApiOperation(value = "Récupérer la liste des Profiles")
    @GetMapping(path="/profiles")
    public List<ProfileResponseDTO> allProfiles()
    {

        return profileService.listProfiles();
    }

    @ApiOperation(value = "ajoute Profile")
    @PostMapping(path="/ajouteprofile")
    public ProfileResponseDTO save(@RequestBody ProfileRequestDTO profileRequestDTO){
        return profileService.addProfile(profileRequestDTO);
    }
    @ApiOperation(value = "Récupérer Profile")
    @GetMapping(path = "/profile/{idProfile}")
    public ProfileResponseDTO getProfile(@PathVariable String idProfile){

        return profileService.getProfile(idProfile);
    }



    @ApiOperation(value = "Update Profile")
    @PutMapping("/update-profile/")
    @ResponseBody
    public void UpdateProfileDTO(@RequestBody ProfileUpdateDTO dto) {
        profileService.updateProfileDTO(dto);
    }

}