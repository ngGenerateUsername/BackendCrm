package com.CRM.Backend.entities.Dto;

import com.CRM.Backend.entities.Produit;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@Builder
public class DTOCategorie {
    private Long idCategorie;
    private String nom;
    private double tva;
    private List<Produit> produits;


}
