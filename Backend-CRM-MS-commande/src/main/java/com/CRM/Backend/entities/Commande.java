package com.CRM.Backend.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Commande implements Serializable {
    @Id
    private Long id;
    @GeneratedValue(strategy = GenerationType.IDENTITY )
        private  int Num;
    @Temporal(TemporalType.DATE)
    Date DateCreation;

    @Temporal(TemporalType.DATE)
    Date DateLivraison;
    private Long idContact;
    private  Long IDResponsableStock;
    private String adressCommande;
    private Long TicketId;
    private Long IdProduit;




}
