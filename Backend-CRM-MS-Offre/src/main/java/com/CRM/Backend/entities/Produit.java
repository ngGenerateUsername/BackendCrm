package com.CRM.Backend.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.List;

@Entity
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

    //add new attribute to this class (devise)    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeDevis typeDevis;
    
    
    @JsonIgnoreProperties(value = {"produits"})
    @ManyToOne
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Categorie categorie;

    @OneToMany(mappedBy = "produit")
    @JsonIgnoreProperties(value = {"produit"})
    private List<LigneOffre> ligneOffres;


    //auto calculate tva when adding new product
    @PrePersist
    @PreUpdate
    private void calculatePrixAvecTva()
    {
        if(categorie != null)
        {
            prixAvecTva = prixInitial * (1 + categorie.getTva()/100);
        }else 
        {
            prixAvecTva = prixInitial;
        }
    }

}
