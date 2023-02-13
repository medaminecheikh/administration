package com.administration.service;

import com.administration.dto.UtilisateurRequestDTO;
import com.administration.dto.UtilisateurResponseDTO;
import com.administration.dto.UtilisateurUpdateDTO;
import com.administration.repo.UtilisateurRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{
    UtilisateurRepo utilisateurRepo;

    public UtilisateurServiceImpl(UtilisateurRepo utilisateurRepo) {
        this.utilisateurRepo = utilisateurRepo;
    }

    @Override
    public UtilisateurResponseDTO addUtilisateur(UtilisateurRequestDTO RequestDTO) {
        return null;
    }

    @Override
    public UtilisateurResponseDTO getUtilisateur(String id) {
        return null;
    }

    @Override
    public List<UtilisateurResponseDTO> listUtilisateurs() {
        return null;
    }

    @Override
    public void updateUtilisateurDTO(UtilisateurUpdateDTO dto) {

    }
}
