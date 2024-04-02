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
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idC;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Num;

    @CreationTimestamp
    @Column(updatable = false,nullable = false)
    private LocalDateTime DateCreation = LocalDateTime.now()  ;

    @CreationTimestamp
    private LocalDateTime DateLivraison;
    private  long prixtotale;
    private Long idContact;
    private Long IDResponsableStock;
    private String adressCommande;
    private Long TicketId;
    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"commande"})
    private List<LigneFacture> lignes;
    private boolean etat;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"commande"})
    private List<LigneCommande> lignesC;


}
