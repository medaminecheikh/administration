package com.administration.entity;

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
@Table(name = "PROFIL")
public class Profil implements Serializable {
    @Id
    private String idProfil;
    @Column
    private String Nom_P;
    @Column
    private String Des_P;

    @ManyToMany
    @JoinTable(
            name = "PROFIL_FONCTION",
            joinColumns = @JoinColumn(name = "idProfiles"),
            inverseJoinColumns = @JoinColumn(name = "idFoncs")
    )
    private List<Fonction> fonctions;
    @OneToMany(mappedBy = "profil", cascade = CascadeType.ALL)
    private List<ProfileUser> profileUsers;
    @ManyToOne
    private Model model;

}
