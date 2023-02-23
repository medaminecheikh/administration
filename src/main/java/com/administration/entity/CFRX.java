package com.administration.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CFRX implements Serializable {
    @Id
    private String idCFRX;
   private String COD_CFRX    ;
   private String COD_ETT  ;
   private String DES_CFRX     ;
   private String PRFX_CFRX    ;
   private String ORG          ;
   private String ADR          ;
}
