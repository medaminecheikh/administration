package com.administration.entity;

import lombok.Getter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Immutable
@Table(name = "user_view")
public class UserView implements Serializable {

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "nom_u")
    private String nomU;

    @Column(name = "nom_p")
    private String nomP;

    @Column(name = "f_adm_cen")
    private int fAdmCen;

    @Column(name = "ett")
    private String ett;

    @Column(name = "dr")
    private String dr;


    @Column(name = "num_caise")
    private int numCaise;

}
