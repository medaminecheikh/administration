package com.administration.controller;

import com.administration.dto.UtilisateurRequestDTO;
import com.administration.dto.UtilisateurResponseDTO;
import com.administration.dto.UtilisateurUpdateDTO;
import com.administration.service.IUtilisateurService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@CrossOrigin(origins="*")
@Api(tags = "Gestion Users")
@Slf4j
public class UtilisateurController {
    IUtilisateurService IUtilisateurService;

    public UtilisateurController(IUtilisateurService IUtilisateurService) {
        this.IUtilisateurService = IUtilisateurService;
    }

    @ApiOperation(value = "Récupérer la liste des Utilisateurs")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path="/utilisateurs")
    public List<UtilisateurResponseDTO> allUtilisateurs()
    {

        return IUtilisateurService.listUtilisateurs();
    }

    @ApiOperation(value = "ajoute Utilisateur")
    @PostMapping(path="/ajouteutilisateur")
    public UtilisateurResponseDTO save(@RequestBody UtilisateurRequestDTO utilisateurRequestDTO){
        return IUtilisateurService.addUtilisateur(utilisateurRequestDTO);
    }

    @ApiOperation(value = "Récupérer Utilisateur")
    @GetMapping(path = "/utilisateur/{idUtilisateur}")
    public UtilisateurResponseDTO getUtilisateur(@PathVariable String idUtilisateur){

        return IUtilisateurService.getUtilisateur(idUtilisateur);
    }

    @ApiOperation(value = "Update Utilisateur")
    @PutMapping("/update-utilisateur/")
    @ResponseBody
    public void UpdateUtilisateurDTO(@RequestBody UtilisateurUpdateDTO dto) {
        IUtilisateurService.updateUtilisateurDTO(dto);
    }

    @ApiOperation(value = "Affecter Profile")
    @PutMapping("/affecterProfiletoUser/{idUser}/{idProfile}")
    public void affecterUserToProfile(@PathVariable String idUser,@PathVariable String idProfile){
        IUtilisateurService.affecterProfileToUser(idUser,idProfile);
    }

    @ApiOperation(value = "Affecter User")
    @PutMapping("/affecterUserToEtt/{idUser}/{idEtt}")
    public void affecterUserToEtt(@PathVariable String idUser,@PathVariable String idEtt){
        IUtilisateurService.affecterUserToEtt(idUser,idEtt);
    }

    @ApiOperation(value = "Remove Ett")
    @PutMapping("/removeEtt/{idUser}")
    public void removeEtt(@PathVariable String idUser){
        IUtilisateurService.removeEtt(idUser);
    }
    @ApiOperation(value = "Remove Profile")
    @PutMapping("/removeProfile/{idUser}")
    public void removeProfile(@PathVariable String idUser){
        IUtilisateurService.removeProfile(idUser);
    }
    
    @ApiOperation(value = "Delete User")
    @DeleteMapping("/deleteUser/{idUser}")
    public void deleteUser(@PathVariable String idUser){
        IUtilisateurService.deleteUser(idUser);
    }

    @ApiOperation(value = "Récupérer la liste des Users")

    @GetMapping(path="/searchPageUsers")
    public List<UtilisateurResponseDTO> searchPageUsers(
            @RequestParam(name = "Keyword",defaultValue = "")String kw,
            @RequestParam (name = "page",defaultValue = "0")int page
            ,@RequestParam(name = "size",defaultValue = "10")int size)
    {

        return IUtilisateurService.findUtilisateurByLogin("%"+kw+"%",page,size);
    }



}
