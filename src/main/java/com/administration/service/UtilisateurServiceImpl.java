package com.administration.service;

import com.administration.dto.UtilisateurRequestDTO;
import com.administration.dto.UtilisateurResponseDTO;
import com.administration.dto.UtilisateurUpdateDTO;
import com.administration.entity.Utilisateur;
import com.administration.mappers.UserMapper;
import com.administration.repo.UtilisateurRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{
    UtilisateurRepo utilisateurRepo;
    UserMapper userMapper;

    public UtilisateurServiceImpl(UtilisateurRepo utilisateurRepo, UserMapper userMapper) {
        this.utilisateurRepo = utilisateurRepo;
        this.userMapper = userMapper;
    }

    @Override
    public UtilisateurResponseDTO addUtilisateur(UtilisateurRequestDTO RequestDTO) {
        Utilisateur utilisateur=userMapper.UtilisateurRequestDTOUtilisateur(RequestDTO);
        utilisateur.setIdUser(UUID.randomUUID().toString());
        utilisateur.setDateInscrit(new Date());
        utilisateurRepo.save(utilisateur);
        UtilisateurResponseDTO utilisateurResponseDTO=userMapper.UtilisateurTOUtilisateurResponseDTO(utilisateur);
        return utilisateurResponseDTO;
    }

    @Override
    public UtilisateurResponseDTO getUtilisateur(String id) {
        Utilisateur utilisateur =utilisateurRepo.findById(id).get();
        UtilisateurResponseDTO utilisateurResponseDTO=userMapper.UtilisateurTOUtilisateurResponseDTO(utilisateur);
        return utilisateurResponseDTO;
    }

    @Override
    public List<UtilisateurResponseDTO> listUtilisateurs() {
        List<Utilisateur> utilisateurs=utilisateurRepo.findAll();
        List<UtilisateurResponseDTO> utilisateurResponseDTOList=utilisateurs.stream()
                .map(utilisateur -> userMapper.UtilisateurTOUtilisateurResponseDTO(utilisateur))
                .collect(Collectors.toList());
        return utilisateurResponseDTOList;
    }

    @Override
    public void updateUtilisateurDTO(UtilisateurUpdateDTO dto) {
            Utilisateur utilisateur=utilisateurRepo.findById(dto.getIdUser()).get();
            userMapper.updateUtilisateurFromDto(dto,utilisateur);
            utilisateurRepo.save(utilisateur);
    }
}
