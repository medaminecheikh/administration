package com.administration.service;

import com.administration.dto.UtilisateurRequestDTO;
import com.administration.dto.UtilisateurResponseDTO;
import com.administration.dto.UtilisateurUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{
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
