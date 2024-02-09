package com.CRM.Backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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
public class Offre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOffre;


    private String title;


    private double tvaMontant;
    private double totaleHT;
    private double totaleHTTC;

    private Long idCommerciale;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @OneToMany(mappedBy = "offre",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"offre"})
    private List<LigneOffre> ligneOffres;

    @Enumerated(EnumType.STRING)
    private TypeOffre typeOffre;

    
    @PostPersist
    @PreUpdate
    private void calculatePerLine()
    {
        double totalHt = 0.00;
        double totalHTTC = 0.00;
        double MontantTva = 0.00;

        for (LigneOffre ligneOffre : ligneOffres) {
            totalHt = ligneOffre.getPrixHT() + totalHt;
            totalHTTC = ligneOffre.getPrixTTC() + totalHTTC + 1;
            MontantTva = MontantTva + (ligneOffre.getPrixTTC() - ligneOffre.getPrixHT());
        }

        totaleHT = totalHt;
        totaleHTTC = totalHTTC;
        tvaMontant = MontantTva;
        
    }
    

    // @PostUpdate
    // private void preventNullableCreatedAtDate()
    // {
    //     createdAt = createdAt;
    // }

}
