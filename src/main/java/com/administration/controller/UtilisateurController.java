package com.administration.controller;

import com.administration.dto.UtilisateurRequestDTO;
import com.administration.dto.UtilisateurResponseDTO;
import com.administration.dto.UtilisateurUpdateDTO;
import com.administration.service.UtilisateurService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UtilisateurController {
    UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }
    @ApiOperation(value = "Récupérer la liste des Utilisateurs")
    @GetMapping(path="/utilisateurs")
    public List<UtilisateurResponseDTO> allUtilisateurs()
    {

        return utilisateurService.listUtilisateurs();
    }

    @ApiOperation(value = "ajoute Utilisateur")
    @PostMapping(path="/ajouteutilisateur")
    public UtilisateurResponseDTO save(@RequestBody UtilisateurRequestDTO utilisateurRequestDTO){
        return utilisateurService.addUtilisateur(utilisateurRequestDTO);
    }
    @ApiOperation(value = "Récupérer Utilisateur")
    @GetMapping(path = "/utilisateur/{idUtilisateur}")
    public UtilisateurResponseDTO getUtilisateur(@PathVariable String idUtilisateur){

        return utilisateurService.getUtilisateur(idUtilisateur);
    }

    @ApiOperation(value = "Update Utilisateur")
    @PutMapping("/update-utilisateur/")
    @ResponseBody
    public void UpdateUtilisateurDTO(@RequestBody UtilisateurUpdateDTO dto) {
        utilisateurService.updateUtilisateurDTO(dto);
    }
    @ApiOperation(value = "Affecter Profile")
    @PutMapping("/affecterProfiletoUser/{idUser}/{idProfile}")
    public void affecterUserToProfile(@PathVariable String idUser,@PathVariable String idProfile){
        utilisateurService.affecterProfileToUser(idUser,idProfile);
    }
}
