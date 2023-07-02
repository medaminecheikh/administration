package com.administration.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OperationEncai implements Serializable {
    @Id
    private String idOp;
    @ManyToOne
    private InfoFacture facture;
    @OneToOne
    private Encaissement encaissement;
    private double total;
}
