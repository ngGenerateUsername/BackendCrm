package com.CRM.Backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
    private Long num;
    private String ref;
    private String description;
    @CreationTimestamp
    @Column(updatable = false,nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime  DatePublication  = LocalDateTime.now();

    @Temporal(TemporalType.DATE)
    private Date dateCloture ;

    private int  quantite ;
    private Long idf;
    private Long idcom;
    private String nometse;
    private Long idproduit;
    private String categorie;
    @Enumerated(EnumType.STRING)
    private etatAO etat;
    private  String nomprod;
    private double tva;


}
