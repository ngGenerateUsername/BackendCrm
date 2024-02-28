package com.CRM.Backend.entities.Dto;

import com.CRM.Backend.entities.Commande;
import com.CRM.Backend.entities.Produit;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Setter
@Getter
@Builder
public class DTOLigneCommande {
    private Long id;
    private int Qte;
    private Double PrixTotale;
    private Commande commande;
    private Produit produit;


}
