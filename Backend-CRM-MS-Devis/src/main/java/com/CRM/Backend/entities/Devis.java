package com.CRM.Backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;
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
public class Devis implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    // @Temporal(TemporalType.DATE)
    // private Date dateCreation;

    private String title;
    
    @CreationTimestamp
    @Column(updatable = false,nullable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    private double montantTotalHT;
    private double montantTotalHTTC;

    @Enumerated(EnumType.STRING)
    private typeDevis typeDevis;

    Long idClient;
    Long idCommercial;

    @OneToMany(mappedBy = "devis",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value={"devis"})
    private List<LigneDevis> lignes;

    
    @ColumnDefault("false") //set default value to 0 (false)
    private boolean timbre;

    @PostPersist
    @PreUpdate
    private void calculatePerLine()
    {
        double totalHt = 0.00;
        double totalHTTC = 0.00;

        for (LigneDevis ligne : lignes) {
            totalHt = ligne.getPrixHT() + totalHt;
            totalHTTC = ligne.getPrixHTTC() + totalHTTC;
        }
        montantTotalHT = totalHt; 
        montantTotalHTTC = timbre?totalHTTC+1.00:totalHTTC;
    }

}
