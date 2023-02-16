package com.administration.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "profile_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUser implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "NOM_P")
    private Profile profile;
    private String COD_CFRX          ;
    private String COD_DR            ;
    private String COD_SRC_ENC       ;
    private String MIS_P             ;
    private String CATEG_PROFIL      ;
}
