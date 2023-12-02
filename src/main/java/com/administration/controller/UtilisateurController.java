package com.administration.controller;

import com.administration.dto.UtilisateurRequestDTO;
import com.administration.dto.UtilisateurResponseDTO;
import com.administration.dto.UtilisateurUpdateDTO;
import com.administration.entity.UserView;
import com.administration.entity.Utilisateur;
import com.administration.service.IUtilisateurService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins="http://localhost:4200")
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
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update-utilisateur")
    @ResponseBody
    public void UpdateUtilisateurDTO(@RequestBody UtilisateurUpdateDTO dto) {
        IUtilisateurService.updateUtilisateurDTO(dto);
    }

    @ApiOperation(value = "Affecter Profile")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/affecterProfiletoUser/{idUser}/{idProfile}")
    public void affecterUserToProfile(@PathVariable String idUser,@PathVariable String idProfile){
        IUtilisateurService.affecterProfileToUser(idUser,idProfile);
    }

    @ApiOperation(value = "Affecter User")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/affecterUserToEtt/{idUser}/{idEtt}")
    public void affecterUserToEtt(@PathVariable String idUser,@PathVariable String idEtt){
        IUtilisateurService.affecterUserToEtt(idUser,idEtt);
    }

    @ApiOperation(value = "Remove Ett")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/removeEtt/{idUser}")
    public void removeEtt(@PathVariable String idUser){
        IUtilisateurService.removeEtt(idUser);
    }
    @ApiOperation(value = "Remove Profile")
    @PutMapping("/removeProfile/{idUser}/{idProfil}")
    public void removeProfile(@PathVariable String idUser,@PathVariable String idProfil){
        IUtilisateurService.removeProfile(idUser,idProfil);
    }
    
    @ApiOperation(value = "Delete User")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleteUser/{idUser}")
    public void deleteUser(@PathVariable String idUser){
        IUtilisateurService.deleteUser(idUser);
    }

    @ApiOperation(value = "Récupérer la liste des Users")

    @GetMapping(path="/searchPageUsers")
    public List<UtilisateurResponseDTO> searchPageUsers(
            @RequestParam(name = "Keyword",defaultValue = "")String kw,
            @RequestParam(name = "nom",defaultValue = "")String nom,
            @RequestParam(name = "prenom",defaultValue = "")String prenom,
            @RequestParam(name = "estActif",defaultValue = "")Integer estActif,
            @RequestParam (name = "page",defaultValue = "0")int page
            ,@RequestParam(name = "size",defaultValue = "10")int size)
    {

        return IUtilisateurService.findUtilisateurByLogin(kw,nom,prenom,estActif,page,size);
    }
    @GetMapping("/searchUserBy")
    public List<UtilisateurResponseDTO> findUtilisateurByAll(
            @RequestParam(name = "login", required = false) String login,
            @RequestParam(name = "prenU", required = false) String prenU,
            @RequestParam(name = "nomU", required = false) String nomU,
            @RequestParam(name = "matricule", required = false) String matricule,
            @RequestParam(name = "estActif", required = false) Integer estActif,
            @RequestParam(name = "is_EXPIRED", required = false) Integer is_EXPIRED,
            @RequestParam(name = "zoneId", required = false) String zoneId,
            @RequestParam(name = "drId", required = false) String drId,
            @RequestParam(name = "ettId", required = false) String ettId,
            @RequestParam(name = "profilId", required = false) String profilId,
            @RequestParam (name = "page",defaultValue = "0")int page
            ,@RequestParam(name = "size",defaultValue = "10")int size) {
        Sort sort = Sort.by("date_EXPIRED");
        PageRequest pageable = PageRequest.of(page, size, sort);

      /*  if (is_EXPIRED != null && Objects.equals(is_EXPIRED, 1)) {
            // Handle the case where is_EXPIRED is not null and equals 1
            return IUtilisateurService.findUtilisateurExpired(login, prenU, nomU, matricule, estActif, is_EXPIRED, zoneId,drId,ettId,profilId, pageable);
        } else if (is_EXPIRED != null && Objects.equals(is_EXPIRED, 0)) {
            // Handle other cases, including when is_EXPIRED is null and  equal to 0
            return IUtilisateurService.findUtilisateurValid(login, prenU, nomU, matricule, estActif, is_EXPIRED, zoneId,drId,ettId,profilId, pageable);
        } else {*/
            return IUtilisateurService.findUtilisateurAll(login, prenU, nomU, matricule, estActif, zoneId,drId,ettId,profilId,is_EXPIRED, pageable);


    }
    @GetMapping("/utilisateurlogin/{username}")
    public ResponseEntity<UtilisateurResponseDTO> getUtilisateurByLogin(@PathVariable String username) {
        UtilisateurResponseDTO utilisateur = IUtilisateurService.getbyLogin(username);

        if (utilisateur == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(utilisateur);
        }
    }
    @GetMapping("/utilisateurbyzone/{zoneId}")
    public ResponseEntity<List<UtilisateurResponseDTO>> getUtilisateurByZone(@PathVariable String zoneId) {
        return ResponseEntity.ok(IUtilisateurService.getUtilisateurbyZone(zoneId));
    }
}
