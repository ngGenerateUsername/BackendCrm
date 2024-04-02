package com.CRM.Backend.entities.Dto;

import com.CRM.Backend.entities.Commande;
import com.CRM.Backend.entities.Produit;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DTOLigneCommande {
    private Long idldc;
    private int Qte;
    private Double PrixTotale;
   // private Commande commande;
 private Long idproduit;
    private String nomproduit;
    private Long idcontact;
    private  boolean passed  = false;
    private String nom;
    private double tva;


}
