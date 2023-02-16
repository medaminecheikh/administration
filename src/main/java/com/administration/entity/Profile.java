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
@Table(name = "SNE.PROFIL")
public class Profile implements Serializable {
    @Id
    @Column(name = "NOM_P")
    private String idProfile;
    private String nomProfile;

    @ManyToMany
    @JoinTable(
            name = "DROIT_ACCES",
            joinColumns = @JoinColumn(name = "NOM_P"),
            inverseJoinColumns = @JoinColumn(name = "COD_F")
    )
    private List<Fonctionalite> fonctionalites;
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<ProfileUser> profileUsers;


}
