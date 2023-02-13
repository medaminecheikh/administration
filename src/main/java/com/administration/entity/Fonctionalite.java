package com.administration.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Fonctionalite implements Serializable {
    @Id
    private String idFonctionalite;
    private String nomFonc;
    @ManyToMany(mappedBy = "functionalites")
    private List<Profile> profiles;
}
