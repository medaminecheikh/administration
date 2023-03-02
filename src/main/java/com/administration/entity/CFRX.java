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
@Table(name = "CFRX")
public class CFRX implements Serializable {
    @Id
    private String idCFRX;
   private String cod_CFRX    ;
   private String cod_ETT  ;
   private String des_CFRX     ;
   private String prfx_CFRX    ;
   private String org          ;
   private String adr          ;
}
