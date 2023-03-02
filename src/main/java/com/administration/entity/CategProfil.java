package com.administration.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

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
   @OneToMany(mappedBy = "categProfil",fetch = FetchType.LAZY)
    private List<Profil> profils;
}
