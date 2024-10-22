package com.CRM.Backend.entities;

// import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class Facture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Titre;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime dateCreation = LocalDateTime.now();

    private double montantTotalHT;
    private double montantTotalHTTC;
    private Long idClient;
    private Long idCommercial;

    @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"facture"})
    private List<LigneFacture> lignes;
}