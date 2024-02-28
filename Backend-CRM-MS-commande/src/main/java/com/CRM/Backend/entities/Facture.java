package com.CRM.Backend.entities;

// import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private String Titre;

    @CreationTimestamp
    @Column(updatable = false,nullable = false)
    private LocalDateTime dateCreation;
    private double montantTotalHT;
    private double montantTotalHTTC;
    Long idClient;
    Long idCommercial;

    @OneToMany(mappedBy = "facture",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value={"facture"})
    private List<LigneFacture> lignes;

    @ColumnDefault("false") //default value 0 (false)
    private boolean timbre;

    @PostPersist
    @PreUpdate
    private void calculatePerLine()
    {
        double totalHt = 0.00;
        double totalHTTC = 0.00;

        for (LigneFacture ligne : lignes) {
            totalHt = ligne.getPrixHT() + totalHt;
            totalHTTC = ligne.getPrixHTTC() + totalHTTC;
        }
        montantTotalHT = totalHt;
        montantTotalHTTC = timbre?totalHTTC+1.00:totalHTTC;
    }

}