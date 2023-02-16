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
@Table(name = "utilisateur")
public class Utilisateur implements Serializable {
    @Id
    private String idUser;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "NOM_P")
    private String nomP;
    @Column(name = "NOM_U")
    private String nomU;
    @Column(name = "PWD_U")
    private String pwdU;
    @Column(name = "PREN_U")
    private String prenU;
    @Column(name = "DESC_U")
    private String descU;
    @Column(name = "EST_ACTIF")
    private int estActif;
    @Column(name = "F_ADM_LOC")
    private int f_ADM_LOC;
    @Column(name = "F_ADM_CEN")
    private int f_ADM_CEN;
    @Column(name = "MATRICULE")
    private String matricule;

    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private ProfileUser profileUser;
    @ManyToOne
    private Ett ett;
}
