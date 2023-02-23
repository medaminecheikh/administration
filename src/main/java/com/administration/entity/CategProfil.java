package com.administration.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategProfil implements Serializable {

    @Id
    private String idCategProfile;
   private String COD_CATEG_P  ;
   private String DES_CATEG_P;
}
