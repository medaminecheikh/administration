package com.administration.service.impl;

import com.administration.entity.Utilisateur;
import com.administration.service.IUtilisateurService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private IUtilisateurService utilisateurService;

    public UserDetailsServiceImpl(IUtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur=utilisateurService.getUtilisateurbyLogin(username);
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        utilisateur.getProfilUser().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getProfil().getNomP()));
        });
        return new User(utilisateur.getLogin(), utilisateur.getPwdU(),authorities);
    }
}
