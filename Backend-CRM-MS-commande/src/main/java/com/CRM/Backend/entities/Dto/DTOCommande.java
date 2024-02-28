package com.CRM.Backend.entities.Dto;

import com.CRM.Backend.entities.LigneCommande;
import com.CRM.Backend.entities.LigneFacture;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
public class DTOCommande {
    private int Num;
    private LocalDateTime DateCreation;
    private LocalDateTime DateLivraison;
    private Long idContact;
    private Long IDResponsableStock;
    private String adressCommande;
    private Long TicketId;
    private List<LigneFacture> lignes;
    private List<LigneCommande> lignesC;


}
