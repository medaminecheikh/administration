package com.administration.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dregionale")
public class Dregional implements Serializable {
    @Id
    private String idDregional;
    private String address;
    private int tel;
    private String email;
    private String codePostal;
    private String chefDirection;
    @OneToMany
    private List<Ett> etts;

}
