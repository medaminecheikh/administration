package com.administration.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "FONCTION")
public class Fonction implements Serializable {
    @Id
    private String idFonc;
    @Column(name = "COD_F")
   private String codF;
    @Column(name = "FON_COD_F")
   private String fon_COD_F;
    @Column(name = "DES_F")
   private String desF;
    @Column(name = "NOM_F")
   private String nomF;
    @Column(name = "NOM_MENU")
   private String nomMENU;
    @Column(name = "F_DROIT_ACCES")
   private int F_DROIT_ACCES;
    @Column(name = "F_ADM")
   private int F_ADM;
    @ManyToMany(mappedBy ="fonctions" )
    private List<Model> models;
    @ManyToMany(mappedBy = "fonctions")
    private List<Profil> profils;
}
