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

    @ManyToMany
    @JoinTable(
            name = "DROIT_ACCES",
            joinColumns = @JoinColumn(name = "NOM_P"),
            inverseJoinColumns = @JoinColumn(name = "COD_F")
    )
    private List<Fonctionalite> fonctionalites;
    @OneToMany(mappedBy = "profile")
    private List<ProfileUser> profileUsers;
    @OneToMany(mappedBy = "profile")
    private List<Utilisateur> utilisateurs;

}
