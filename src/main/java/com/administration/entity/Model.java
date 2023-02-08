package com.administration.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Model implements Serializable {
    @Id
    private String idModel;
    private boolean consulter;
    private boolean ajouter;
    private boolean modifier;

    private Status status;
    private Role role;
}
