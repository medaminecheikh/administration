package com.administration.entity;

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
@Table(name = "zone")
public class Zone implements Serializable {
    @Id
    private String codZone ;
    private String codEtt;
    @Column(name = "DES_CFRX")
    private String desCFRX;
    @Column(name = "PRFX_CFRX")
    private String prfxCFRX;
    @Column(name = "ORG")
    private String org;
    @Column(name = "ADR")
    private String adr;
}
