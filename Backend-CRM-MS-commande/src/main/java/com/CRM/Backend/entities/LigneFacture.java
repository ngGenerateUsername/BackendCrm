package com.CRM.Backend.entities;


import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LigneFacture implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "Idcommande")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "IdFacture")
    private Facture facture;
    @JsonIgnoreProperties(value = {"lignes"})

    @CreationTimestamp
    @Column(updatable = false)
    private   LocalDateTime DateCreation;
    private double prixHT;
    private double prixHTTC;


}