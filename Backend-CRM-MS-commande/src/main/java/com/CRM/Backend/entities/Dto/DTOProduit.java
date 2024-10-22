package com.CRM.Backend.entities.Dto;

import com.CRM.Backend.entities.Categorie;
import com.CRM.Backend.entities.LigneCommande;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.*;

import java.math.BigInteger;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class DTOProduit {
    private Long idProduit;
    private String reference;
    private String nom;
    private String description;
    private double prixInitial;
    private double prixAvecTva;
    private int Qte;
    private int MinQte;
    private Long idcategorie;
   // private List<LigneCommande> lignesC;
    private Long idEntreprise;



}
