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
    private String idZone;
    private String nomZone;
    private int nbrVille;
@OneToMany
    private List<Dregional> dregionals;
}
