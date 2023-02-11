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
@Getter
@Setter
@NoArgsConstructor
public class Profile implements Serializable {
    @Id
    private String idProfile;
    private String nomProfile;
    @OneToMany(mappedBy = "profile")
    private List<Model> models;
    @ManyToMany
    @JoinTable(
            name = "dmit_accee",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "functionalite_id")
    )
    private List<Fonctionalite> functionalites;
    @ManyToMany
    @JoinTable(
            name = "users_profils",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "utilistateur_id")
    )
    private List<Utilisateur> utilisateurs;
}
