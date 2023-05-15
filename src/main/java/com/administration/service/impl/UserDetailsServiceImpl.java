package com.administration.service.impl;

import com.administration.entity.Utilisateur;
import com.administration.service.IUtilisateurService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private IUtilisateurService utilisateurService;

    public UserDetailsServiceImpl(IUtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurService.getUtilisateurbyLogin(username);

        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé");
        }

        if(utilisateur.getEstActif() == 0) {
            throw new BadCredentialsException("Le compte est désactivé.");
        }

        if(utilisateur.getIs_EXPIRED() == 1 ) {
            throw new CredentialsExpiredException("Le compte a expiré.");
        }


        Collection<GrantedAuthority> authorities = new ArrayList<>();
        utilisateur.getProfilUser().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getProfil().getNomP()));
        });
        return new User(utilisateur.getLogin(), utilisateur.getPwdU(), utilisateur.getEstActif() == 1, true, true, utilisateur.getIs_EXPIRED() == 0, authorities);
    }

}
