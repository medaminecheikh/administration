package com.administration.controller;

import com.administration.dto.UtilisateurRequestDTO;
import com.administration.dto.UtilisateurResponseDTO;
import com.administration.dto.UtilisateurUpdateDTO;
import com.administration.security.JWTUtils;
import com.administration.service.IUtilisateurService;
import com.administration.entity.LoginRequest;
import com.administration.entity.Utilisateur;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.JWTVerifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    @PostMapping("/login")
    public ResponseEntity<Utilisateur> login(@RequestBody LoginRequest loginRequest) {
        Utilisateur user = IUtilisateurService.getUtilisateurbyLogin(loginRequest.getUsername());
        if (user != null && user.getPwdU().equals(loginRequest.getPassword())) {
            return new ResponseEntity<Utilisateur>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<Utilisateur>(HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/refreshtoken")
    public void RefreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String authorizationtoken = request.getHeader(JWTUtils.AUTH_HEADER);
        log.info(authorizationtoken +"authorizationtoken FOUND");
        if (authorizationtoken != null && authorizationtoken.startsWith(JWTUtils.PREFIX)) {
            try {log.info("TOKEN NOT NULL AND PREFIX FOUND");
                String jwt = authorizationtoken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256(JWTUtils.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                String username = decodedJWT.getSubject();
                Utilisateur user = IUtilisateurService.getUtilisateurbyLogin(username);//on peut verifier blacklist apres
                //create token
                String jwtAccesToken = JWT.create()
                        .withSubject(user.getLogin())
                        .withExpiresAt(new Date(System.currentTimeMillis() +JWTUtils.EXPIRE_ACCESS))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getProfilUser().stream().map(
                                r -> r.getProfil().getNomP()).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> idToken = new HashMap<>();
                idToken.put("Access-token", jwtAccesToken);
                idToken.put("Refresh-token", jwt);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);

            } catch (Exception e) {
                throw e;
            }

        }else throw new RuntimeException("Refresh token required !!");
    }
}
