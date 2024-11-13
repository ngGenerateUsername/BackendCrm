package com.CRM.Backend.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Produit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduit;
    private String reference;
    private String nom;
    private String description;
    private double prixInitial;
    private double prixAvecTva;
    private int Qte;
    private int MinQte;
    private Long IdEntreprise;
    @JsonIgnoreProperties(value = {"produits"})
    @ManyToOne
    private Categorie categorie;

}




