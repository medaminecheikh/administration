package com.administration.controller;

import com.administration.entity.LoginRequest;
import com.administration.entity.Utilisateur;
import com.administration.entity.AuthResponse;
import com.administration.security.Jwt.JwtTokenUtil;
import com.administration.security.Jwt.JwtVariables;
import com.administration.service.IUtilisateurService;
import com.administration.service.impl.UserDetailsServiceImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
@Slf4j
@AllArgsConstructor
public class AuthController {

    private IUtilisateurService IUtilisateurService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> login(@RequestParam String username,@RequestParam String password) {
        try {
            log.info("Attempting authentication for user: " + username);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            String accessToken = jwtTokenUtil.generateAccessToken(userDetails);
            String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
            AuthResponse authResponse = new AuthResponse(userDetails.getUsername(),
                    userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()),
                    accessToken, refreshToken);
            return ResponseEntity.ok(authResponse);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/refreshtoken")
    public void RefreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String authorizationtoken = request.getHeader(JwtVariables.AUTH_HEADER);
        log.info(authorizationtoken +"authorizationtoken FOUND");
        if (authorizationtoken != null && authorizationtoken.startsWith(JwtVariables.PREFIX)) {
            try {log.info("TOKEN NOT NULL AND PREFIX FOUND");
                String jwt = authorizationtoken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256(JwtVariables.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                String username = decodedJWT.getSubject();
                Utilisateur user = IUtilisateurService.getUtilisateurbyLogin(username);//on peut verifier blacklist apres
                //create token
                String jwtAccesToken = JWT.create()
                        .withSubject(user.getLogin())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtVariables.EXPIRE_ACCESS))
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
