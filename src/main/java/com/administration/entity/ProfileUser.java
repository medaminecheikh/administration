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
@Table(name = "SNE.PROFIL_USER")
public class ProfileUser implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NOM_P")
    private Profile profile;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Utilisateur utilisateur;
    private String COD_CFRX          ;
    private String COD_DR            ;
    private String COD_SRC_ENC       ;
    private String MIS_P             ;
    private String CATEG_PROFIL      ;
}
