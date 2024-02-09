package com.CRM.Backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Categorie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long idCategorie;
    
    private String nom;
    private double tva;

    @OneToMany(mappedBy = "categorie")
    @JsonIgnoreProperties(value = {"categorie"})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Produit> produits;

}
