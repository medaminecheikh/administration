package com.administration.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PROFIL_USER")
public class ProfilUser implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProfil")
    private Profil profil;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private Utilisateur utilisateur;

    @Column (name = "NOM_P")
    private String nom_P;
    private String COD_CFRX;
    private String COD_ZONE          ;
    private String COD_DR            ;
    private String COD_ETT       ;
    private String MIS_P             ;
    private String CATEG_PROFIL      ;
}
