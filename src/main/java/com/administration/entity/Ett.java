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
@Table(name = "ett")
public class Ett implements Serializable {
    @Id
    private String idEtt;
    private String address;
    private  int tel;
    private  String email;
    private boolean disponibilite;
    @ManyToMany
    @JoinTable(
            name = "users_profils",
            joinColumns = @JoinColumn(name = "ett_id"),
            inverseJoinColumns = @JoinColumn(name = "utilistateur_id")
    )
    private List<Utilisateur> utilisateurs;
}
