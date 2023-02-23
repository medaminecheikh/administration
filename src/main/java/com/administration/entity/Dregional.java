package com.administration.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DR")
public class Dregional implements Serializable {
    @Id
    private String idDr;
    @Column(name = "COD_DR")
    private String cod_DR;
    @Column(name = "DR")
    private String dr;
    @Column(name = "DR_AR")
    private String drAr;
    @ManyToOne
    private Zone zone;
    @OneToMany(mappedBy = "dregional")
    private List<Ett> etts;

}
