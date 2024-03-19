package com.CRM.Backend.entities;

// import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private String Titre;

    @CreationTimestamp
    @Column(updatable = false,nullable = false)
    private LocalDateTime dateCreation = LocalDateTime.now()  ;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public LocalDateTime getDateCreation(LocalDateTime dateCreation) {
        return this.dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public double getMontantTotalHT() {
        return montantTotalHT;
    }

    public void setMontantTotalHT(double montantTotalHT) {
        this.montantTotalHT = montantTotalHT;
    }

    public double getMontantTotalHTTC() {
        return montantTotalHTTC;
    }

    public void setMontantTotalHTTC(double montantTotalHTTC) {
        this.montantTotalHTTC = montantTotalHTTC;
    }

    public Long getIdClient(Long idClient) {
        return this.idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Long getIdCommercial() {
        return idCommercial;
    }

    public void setIdCommercial(Long idCommercial) {
        this.idCommercial = idCommercial;
    }

    public List<LigneFacture> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneFacture> lignes) {
        this.lignes = lignes;
    }

    public boolean isTimbre() {
        return timbre;
    }

    public void setTimbre(boolean timbre) {
        this.timbre = timbre;
    }
}