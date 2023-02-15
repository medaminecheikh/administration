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
public class Model implements Serializable {
    @Id
    private String idModel;
    @Column(name = "DES_MOD")
    private String desMOD;
    @Column(name = "OBS")
    private String obs;
}
