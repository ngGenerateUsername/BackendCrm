package com.CRM.Backend.entities;


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
public class LigneFacture implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;


    private Long idOffre;

    @JsonIgnoreProperties(value = {"lignes"})
    @ManyToOne
    @JoinColumn(name = "facture_id")
    private Facture facture;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;


    private double prixHT;
    private double prixHTTC;


}
