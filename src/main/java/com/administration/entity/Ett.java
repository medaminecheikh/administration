package com.administration.entity;

import com.sun.istack.Nullable;
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
    @Column(name = "COD_ETT")
    private String codEtt;
    private String COD_Zone;
    private String COD_DR;
    @Column(name = "DES_SRC_ENC")
    private String des_SRC_ENC;
    @Column(name = "PRFX_SRC_ENC")
    private String prfx_SRC_ENC;
    @Column(name = "ADR")
    private String adr;
    @Column(name = "IS_BSCS")
    private int is_BSCS;
}
