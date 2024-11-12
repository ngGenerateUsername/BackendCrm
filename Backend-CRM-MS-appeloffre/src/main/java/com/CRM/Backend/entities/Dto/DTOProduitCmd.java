package com.CRM.Backend.entities.Dto;

import com.CRM.Backend.entities.Categorie;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DTOProduitCmd {
    private String reference;

    private Long idProduit;
    private String nom;
    private String description;
    private double prixAvecTva;
    @JsonIgnoreProperties(value = {"produits"})

    private Categorie categorie;



}
