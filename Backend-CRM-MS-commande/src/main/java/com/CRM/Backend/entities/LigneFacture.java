package com.CRM.Backend.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LigneFacture implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Idcommande")
    @JsonBackReference  // Prevents recursion for 'commande'
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "IdFacture")
    @JsonBackReference  // Prevents recursion for 'facture'
    private Facture facture;

    private int qte;
    private String nom;
    private String cat;
    private Double tva;
    private String ref;
    private double UprixHT;
    private double UprixTTC;
    private double totaleprixHT;
    private double totaleprixTTC;
}
