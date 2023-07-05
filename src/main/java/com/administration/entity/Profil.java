package com.administration.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

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
    private String nomP;
    @Column
    private String Des_P;

    @ManyToMany
    @JoinTable(
            name = "PROFIL_FONCTION",
            joinColumns = @JoinColumn(name = "idProfiles"),
            inverseJoinColumns = @JoinColumn(name = "idFoncs")
    )
    private Set<Fonction> fonctions;
    @OneToMany(mappedBy = "profil", cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    private List<ProfilUser> profilUsers;
    @ManyToOne
    private Model model;


}
