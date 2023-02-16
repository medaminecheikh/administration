package com.administration.entity;

import com.sun.istack.NotNull;
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
@Table(name = "SNE.FONCTION")
public class Fonctionalite implements Serializable {
    @Id
    @Column(name = "COD_F")
   private String codF;
    @Column(name = "FON_COD_F")
   private String FON_COD_F;
    @Column(name = "DES_F")
   private String desF;
    @Column(name = "NOM_F")
   private String nomF;
    @Column(name = "NOM_MENU")
   private String nomMENU;
    @Column(name = "F_DROIT_ACCES")
   @NotNull
   private int F_DROIT_ACCES;
    @Column(name = "F_ADM")
   @NotNull
   private int F_ADM;
}
