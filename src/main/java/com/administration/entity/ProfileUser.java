package com.administration.entity;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "profile_user")
public class ProfileUser implements Serializable {

    private String NOM_P             ;
    private String COD_CFRX          ;
    private String COD_DR            ;
    private String COD_SRC_ENC       ;
    private String MIS_P             ;
    private String CATEG_PROFIL      ;
}
