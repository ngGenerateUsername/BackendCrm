package com.CRM.Backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Produit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int idProduit;
    private String reference;
    private String nom;
    private String description;
    private Long prix;
    private Long quantiteProduit;



    @ManyToMany
    @JsonIgnore
    private List<Offre> offres;

}
