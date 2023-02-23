package com.administration.controller;

import com.administration.dto.ProfilRequestDTO;
import com.administration.dto.ProfilResponseDTO;
import com.administration.dto.ProfilUpdateDTO;
import com.administration.service.ProfilService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Profile management")
public class ProfileController {
    
     ProfilService profilService;

    public ProfileController(ProfilService profilService) {
        this.profilService = profilService;
    }

    @ApiOperation(value = "Récupérer la liste des Profiles")
    @GetMapping(path="/profiles")
    public List<ProfilResponseDTO> allProfiles()
    {

        return profilService.listProfiles();
    }

    @ApiOperation(value = "ajoute Profile")
    @PostMapping(path="/ajouteprofile")
    public ProfilResponseDTO save(@RequestBody ProfilRequestDTO profilRequestDTO){
        return profilService.addProfile(profilRequestDTO);
    }
    @ApiOperation(value = "Récupérer Profile")
    @GetMapping(path = "/profile/{idProfile}")
    public ProfilResponseDTO getProfile(@PathVariable String idProfile){

        return profilService.getProfile(idProfile);
    }



    @ApiOperation(value = "Update Profile")
    @PutMapping("/update-profile/")
    @ResponseBody
    public void UpdateProfileDTO(@RequestBody ProfilUpdateDTO dto) {
        profilService.updateProfileDTO(dto);
    }

    @ApiOperation(value = "Affecter Fonctionalite")
    @PutMapping("/affecterFonctionaliteToProfile/{idFonc}/{idProfile}")
    public void affecterFonctionaliteToProfile(@PathVariable String idFonc,@PathVariable String idProfile){
        profilService.affecterFoncToProfile(idFonc,idProfile);
    }
    @ApiOperation(value = "Remove Fonctionalite")
    @PutMapping("/removeFonc/{idFonc}/{idProfile}")
    public void removeFonc(@PathVariable String idFonc,@PathVariable String idProfile){
        profilService.removeFonc(idFonc,idProfile);
    }

    @ApiOperation(value = "Affecter Model")
    @PutMapping("/affecterModelToProfile/{idModel}/{idProfile}")
    public void affecterModelToProfile(@PathVariable String idModel,@PathVariable String idProfile){
        profilService.affecterModelToProfile(idModel,idProfile);
    }

    @ApiOperation(value = "remove Model")
    @PutMapping("/removeModelToProfile/{idModel}/{idProfile}")
    public void removeModelToProfile(@PathVariable String idModel,@PathVariable String idProfile){
        profilService.removeModel(idProfile);
    }
    @ApiOperation(value = "Delete Profile")
    @DeleteMapping("/deleteProfile/{idProfile}")
    public void deleteProfile(@PathVariable String idProfile){
        profilService.deleteProfile(idProfile);
    }

}
