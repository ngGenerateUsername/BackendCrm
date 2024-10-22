package com.CRM.Backend.entities.Dto;

import com.CRM.Backend.entities.LigneCommande;
import com.CRM.Backend.entities.LigneFacture;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DTOCommande {
    private  Long idC;
    private int Num;
    @CreationTimestamp
    @Column(updatable = false,nullable = false)
    private LocalDateTime DateCreation = LocalDateTime.now()  ;
    private LocalDateTime DateLivraison;
    private Long idContact;
    private Long IDResponsableStock;
    private String adressCommande;
    private Long TicketId;
    //private List<LigneFacture> lignes;
    //private List<DTOLigneCommande> lignesC;
    private boolean etat;

    private  long prixtotale;
    private Long idetse;

    private String nomClient;
}

