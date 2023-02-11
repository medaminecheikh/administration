package com.administration.dto;

import com.administration.entity.Utilisateur;
import lombok.Data;

import java.util.List;

@Data
public class EttResponseDTO {
    private String idEtt;
    private String address;
    private  int tel;
    private  String email;
    private boolean disponibilite;
    private List<Utilisateur> utilisateurs;
}
