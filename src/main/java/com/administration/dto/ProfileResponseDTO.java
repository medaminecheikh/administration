package com.administration.dto;

import com.administration.entity.Fonctionalite;
import com.administration.entity.Model;
import com.administration.entity.Utilisateur;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Data
public class ProfileResponseDTO {
    private String idProfile;
    private String nomProfile;
    private ModelUpdateDTO model;
    private List<FoncUpdateDTO> functionalites;
    private List<Utilisateur> utilisateurs;
}
