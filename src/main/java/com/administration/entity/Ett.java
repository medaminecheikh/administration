package com.administration.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ett")
public class Ett implements Serializable {
    @Id
    private String idEtt;
    @Column(name = "COD_ETT")
    private String codEtt;
    @Column(name = "DES_SRC_ENC")
    private String des_SRC_ENC;
    private String COD_CFRX;
    @Column(name = "PRFX_SRC_ENC")
    private String prfx_SRC_ENC;
    @Column(name = "ADR")
    private String adr;
    @Column(name = "IS_BSCS")
    private int is_BSCS;
    @ManyToOne
    @JoinColumn(name = "COD_DR")
    private Dregional dregional;
    @OneToMany(mappedBy = "ett")
    private List<Utilisateur> utilisateurs;
    @OneToMany(mappedBy = "cod_ett")
    private List<Caisse> caisses;
}
