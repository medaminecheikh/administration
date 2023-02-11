package com.administration.dto;

import com.administration.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurRequestDTO {

    private String refContrat;
    private int cin;
    private String nom;
    private String prenom;
    private String email;
    private String pays;
    private String ville;
    private String address;
    private int tel;
    private Date dateNaissance;
    private Date dateInscrit;
    private Date datePaiment;
    private boolean paiment;
    private Status status;
}
