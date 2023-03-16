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
        Utilisateur utilisateur = utilisateurService.getUtilisateurbyLogin(username);
        return new UtilisateurDetails(utilisateur);
    }

    private static class UtilisateurDetails implements UserDetails {

        private Utilisateur utilisateur;

        public UtilisateurDetails(Utilisateur utilisateur) {
            this.utilisateur = utilisateur;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            utilisateur.getProfilUser().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getProfil().getNomP()));
            });
            return authorities;
        }

        @Override
        public String getPassword() {
            return utilisateur.getPwdU();
        }

        @Override
        public String getUsername() {
            return utilisateur.getLogin();
        }

        @Override
        public boolean isAccountNonExpired() {
            return utilisateur.getIs_EXPIRED() == 0;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return utilisateur.getEstActif() == 1;
        }
    }

}
