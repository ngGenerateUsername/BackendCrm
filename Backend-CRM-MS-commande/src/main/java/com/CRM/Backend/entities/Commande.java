package com.CRM.Backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Num;

    @CreationTimestamp
    private LocalDateTime DateCreation;

    @CreationTimestamp
    private LocalDateTime DateLivraison;

    private Long idContact;
    private Long IDResponsableStock;
    private String adressCommande;
    private Long TicketId;
    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"commande"})
    private List<LigneFacture> lignes;


    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"commande"})
    private List<LigneCommande> lignesC;


}
