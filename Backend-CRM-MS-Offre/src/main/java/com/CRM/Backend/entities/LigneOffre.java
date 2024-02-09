package com.CRM.Backend.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LigneOffre implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = {"ligneOffres"})
    private Produit produit;

    @ManyToOne
    @JsonIgnoreProperties(value = {"ligneOffres"})
    @JoinColumn(nullable = true)
    private Offre offre;

    private Long qte;
    private double remise;
    private double prixHT;
    private double PrixTTC;


    //in this function we will calculate prixHT and prixTTC
    @PrePersist
    @PreUpdate
    private void calculateInSaveOrUpdate()
    {
        //prix sans calcule de tva
        this.prixHT = this.qte * this.produit.getPrixInitial();
        if(this.remise!= 0.00)
            this.prixHT = this.prixHT - (this.prixHT * this.remise/100);
        
        //including tva
        this.PrixTTC = this.prixHT * (1 + this.getProduit().getCategorie().getTva()/100);

    }
   
}
