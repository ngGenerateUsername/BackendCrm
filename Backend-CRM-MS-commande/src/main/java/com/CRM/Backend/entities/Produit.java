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
    private  double TVA;
    private int Qte;
    private int MinQte;



    //auto calculate tva when adding new product
    @PrePersist
    @PreUpdate
    private void calculatePrixAvecTva()
    {
            prixAvecTva = prixInitial * (1 + TVA /100);

    }


    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrixInitial() {
        return prixInitial;
    }

    public void setPrixInitial(double prixInitial) {
        this.prixInitial = prixInitial;
    }

    public double getPrixAvecTva() {
        return prixAvecTva;
    }

    public void setPrixAvecTva(double prixAvecTva) {
        this.prixAvecTva = prixAvecTva;
    }

    public double getTVA() {
        return TVA;
    }

    public void setTVA(double TVA) {
        this.TVA = TVA;
    }

    public int getQte() {
        return Qte;
    }

    public void setQte(int qte) {
        Qte = qte;
    }

    public int getMinQte() {
        return MinQte;
    }

    public void setMinQte(int minQte) {
        MinQte = minQte;
    }
}