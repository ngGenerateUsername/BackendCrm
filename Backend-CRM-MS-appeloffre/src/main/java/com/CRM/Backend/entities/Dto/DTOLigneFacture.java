package com.CRM.Backend.entities.Dto;

import com.CRM.Backend.entities.Commande;
import com.CRM.Backend.entities.Facture;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class DTOLigneFacture {
    private Long id;
    private Commande commande;
    private Facture facture;
    private LocalDateTime DateCreation;
    private double prixHT;
    private double prixHTTC;



}
