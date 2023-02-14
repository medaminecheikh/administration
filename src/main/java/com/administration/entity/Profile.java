package com.administration.entity;

import com.sun.istack.Nullable;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Profile implements Serializable {
    @Id
    private String idProfile;
    private String nomProfile;
    @ManyToOne
    private Model model;
    @ManyToMany

    private List<Fonctionalite> functionalites;
    @ManyToMany
    @JoinTable(
            name = "users_profils",
            joinColumns = @JoinColumn(name = "profile_id",nullable = true),
            inverseJoinColumns = @JoinColumn(name = "utilistateur_id")
    )
    private List<Utilisateur> utilisateurs;
}
