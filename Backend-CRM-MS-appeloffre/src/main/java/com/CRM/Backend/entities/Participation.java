package com.CRM.Backend.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Data
@Builder
public class Participation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    private Long id;
    private int prix;
    @Temporal(TemporalType.DATE)
    private Date dateLivraisonF;
    private Long IdFournisseur;
    @Temporal(TemporalType.DATE)
    private Date publicationoffre = new Date();;
    @ManyToOne
    @JoinColumn(name = "IdAppelOffre")
    @JsonIgnore
    private Appeloffre appeloffre ;



}
