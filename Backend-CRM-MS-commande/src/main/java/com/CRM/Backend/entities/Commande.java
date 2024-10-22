package com.CRM.Backend.entities;

import com.CRM.Backend.entities.LigneCommande;
import com.CRM.Backend.entities.LigneFacture;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idC;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Num;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    @   JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime DateCreation = LocalDateTime.now();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @CreationTimestamp
    private LocalDateTime DateLivraison;

    private long prixtotale;
    private Long idClient;
    private Long IDResponsableStock;
    private String adressCommande;
    private Long TicketId;
    private Long idetse;
    private boolean etat;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"facture"})
    private List<LigneFacture> lignes;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"commande"})
    private List<LigneCommande> lignesC;

    private String nomClient;
}
