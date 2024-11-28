package com.CRM.Backend.entities;

import com.CRM.Backend.repositories.AORepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Appeloffre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idao;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long num;

    private String ref;
    private String description;

      @Temporal(TemporalType.DATE)
    private Date datePublication = new Date();

    @Temporal(TemporalType.DATE)
    private Date dateCloture;

    @Temporal(TemporalType.DATE)
    private Date dateLivraisonAO;

    private int quantite;
    private Long idf;
    private Long idcom;
    private String nometse;
    private Long idproduit;
    private String categorie;

    @Enumerated(EnumType.STRING)
    private etatAO etat;

    private String nomprod;
    private double tva;
    private Long idetse;
    private String descriptionProduit;

    @OneToMany(mappedBy = "appeloffre", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Participation> pcs;

    public void generateNum(AORepository repository) {
        this.num = repository.findMaxNum() + 1; // Get max and increment
    }

    // Custom setter for dateCloture
    public void setDateCloture(Date dateCloture) {
        this.dateCloture = dateCloture;
        if (dateCloture != null) {
            setDateLivraisonAO(calculateDateLivraisonAO(dateCloture));
        } else {
            setDateLivraisonAO(null); // Reset dateLivraisonAO if dateCloture is null
        }
    }

    // Private setter for dateLivraisonAO to prevent direct modification
    private void setDateLivraisonAO(Date dateLivraisonAO) {
        this.dateLivraisonAO = dateLivraisonAO;
    }

    // Helper method to calculate dateLivraisonAO
    private Date calculateDateLivraisonAO(Date dateCloture) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateCloture);
        calendar.add(Calendar.DAY_OF_MONTH, 15); // Add 15 days to dateCloture
        return calendar.getTime();
    }
}
