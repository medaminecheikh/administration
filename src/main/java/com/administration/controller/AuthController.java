package com.administration.controller;

import com.administration.entity.AuthResponse;
import com.administration.entity.Utilisateur;
import com.administration.security.Jwt.JwtTokenUtil;
import com.administration.security.Jwt.JwtVariables;
import com.administration.service.IUtilisateurService;
import com.administration.service.impl.UserDetailsServiceImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import java.util.Date;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
@Slf4j
@AllArgsConstructor
@Api(tags = "Auth Controller")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserDetailsServiceImpl userDetailsService;
    private IUtilisateurService utilisateurService;


    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> login(@RequestParam String username, @RequestParam String password) {

        try {
            log.info("Attempting authentication for user: " + username);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            Algorithm algorithm = Algorithm.HMAC256(JwtVariables.SECRET);

            // Perform the expiration check after authentication
            Utilisateur utilisateur = utilisateurService.getUtilisateurbyLogin(username);
            Date today = new Date();
            Date utilisateurExpirationDate = utilisateur.getDate_EXPIRED();
            if (utilisateurExpirationDate == null || utilisateurExpirationDate.after(today)) {
                // Generate tokens and create AuthResponse
                String accessToken = JWT.create()
                        .withSubject(userDetails.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtVariables.EXPIRE_ACCESS))
                        .withClaim("roles", userDetails.getAuthorities().stream().map(
                                GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .sign(algorithm);

                String refreshToken = JWT.create()
                        .withSubject(userDetails.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtVariables.EXPIRE_REFRESH))
                        .withClaim("roles", userDetails.getAuthorities().stream().map(
                                GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .sign(algorithm);

                AuthResponse authResponse = new AuthResponse(userDetails.getUsername(),
                        userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()),
                        accessToken, refreshToken);
                return ResponseEntity.ok(authResponse);
            } else {
                // Handle the scenario when utilisateur's date has expired
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

//    @GetMapping("/refreshtoken")
//    public ResponseEntity<AuthResponse> RefreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        String authorizationtoken = request.getHeader(JwtVariables.AUTH_HEADER);
//        log.info(authorizationtoken + "authorizationtoken FOUND");
//        if (authorizationtoken != null && authorizationtoken.startsWith(JwtVariables.PREFIX)) {
//            try {
//                log.info("TOKEN NOT NULL AND PREFIX FOUND");
//                String jwt = authorizationtoken.substring(7);
//                Algorithm algorithm = Algorithm.HMAC256(JwtVariables.SECRET);
//                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
//                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
//                String username = decodedJWT.getSubject();
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);//on peut verifier blacklist apres
//                if (!userDetails.isAccountNonExpired() && userDetails.isEnabled()) {
//
//                    //create token
//                    String accessToken = JWT.create()
//                            .withSubject(userDetails.getUsername())
//                            .withExpiresAt(new Date(System.currentTimeMillis() + JwtVariables.EXPIRE_ACCESS))
//                            .withClaim("roles", userDetails.getAuthorities().stream().map(
//                                    GrantedAuthority::getAuthority).collect(Collectors.toList()))
//                            .sign(algorithm);
//
//                    String refreshToken = JWT.create()
//                            .withSubject(userDetails.getUsername())
//                            .withExpiresAt(new Date(System.currentTimeMillis() + JwtVariables.EXPIRE_REFRESH))
//                            .withClaim("roles", userDetails.getAuthorities().stream().map(
//                                    GrantedAuthority::getAuthority).collect(Collectors.toList()))
//                            .sign(algorithm);
//
//                    AuthResponse authResponse = new AuthResponse(userDetails.getUsername(),
//                            userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()),
//                            accessToken, refreshToken);
//                    return ResponseEntity.ok(authResponse);
//                } else ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//            } catch (Exception e) {
//                throw e;
//            }
//
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//    }
}

