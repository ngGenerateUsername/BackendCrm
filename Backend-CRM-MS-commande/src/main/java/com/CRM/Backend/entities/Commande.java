package com.CRM.Backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonFormat;

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
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")

    private LocalDateTime DateCreation = LocalDateTime.now()  ;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @CreationTimestamp
    private LocalDateTime DateLivraison;
    private  long prixtotale;
    private Long idClient;
    private Long IDResponsableStock;
    private String adressCommande;
    private Long TicketId;
    private Long idetse;
    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"commande"})
    private List<LigneFacture> lignes;
    private boolean etat;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"commande"})
    private List<LigneCommande> lignesC;


}
