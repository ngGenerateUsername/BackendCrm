package com.CRM.Backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LigneCommande implements Serializable {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long idldc;

    private int Qte;
    private Double PrixTotale;
    private Long idContact;


    @ManyToOne
    @JoinColumn(name = "IdCommande")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "IdProduit")
    @JsonIgnore

    private Produit produit;









}
