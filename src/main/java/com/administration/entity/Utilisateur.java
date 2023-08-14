package com.administration.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
@Table(name = "UTILISATEUR")
public class Utilisateur implements Serializable {
    @Id
    private String idUser;
    @Column(name = "LOGIN")
    @NotNull
    private String login;
    @Column(name = "NOM_P")
    private String nomP;
    @Column(name = "NOM_U")
    @NotNull
    private String nomU;
    @Column(name = "PWD_U")
    @NotNull
    private String pwdU;
    @NotNull
    @Transient
    private String confirmedpassword;
    @Column(name = "PREN_U")
    @NotNull
    private String prenU;
    @Column(name = "DESC_U")
    @NotNull
    private String descU;
    @Column(name = "EST_ACTIF")
    @NotNull
    private int estActif;
    @Column(name = "F_ADM_LOC")
    @NotNull
    private int f_ADM_LOC;
    @Column(name = "F_ADM_CEN")
    @NotNull
    private int f_ADM_CEN;
    @Column(name = "MATRICULE")
    @NotNull
    private String matricule;
    @Temporal(TemporalType.DATE)
    private Date date_CREATION;
    private int is_EXPIRED;
    @Temporal(TemporalType.DATE)
    private Date date_EXPIRED;
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProfilUser> profilUser;
    @ManyToOne
    private Ett ett;
    @OneToOne
    private Caisse caisse;

}
