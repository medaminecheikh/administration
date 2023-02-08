package com.administration.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

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
}
