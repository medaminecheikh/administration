package com.administration.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_table")
public class User implements Serializable {
@Id
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
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @Temporal(TemporalType.DATE)
    private Date dateInscrit;
    @Temporal(TemporalType.DATE)
    private Date datePaiment;

    private boolean paiment;
    private Status status;


}
