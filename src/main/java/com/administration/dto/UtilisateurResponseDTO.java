package com.administration.dto;

import com.administration.entity.Status;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class UtilisateurResponseDTO {
    private String idUser;
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
