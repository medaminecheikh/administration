package com.administration.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OperationEncai implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idOp;
    @ManyToOne
    private InfoFacture facture;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Encaissement encaissement;
    private double total;
}
