package com.CRM.Backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Data
@Builder
public class Participationdetail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    private Long id;
    private int prix;
    @Temporal(TemporalType.DATE)
    private Date datesoummision ;
    private String adresse;
private String mail;
    private String telephone;
    private String role;
    private String username;
private String doamine;
    @ManyToOne
    @JoinColumn(name = "IdAppelOffre")
    @JsonIgnore
    private Appeloffre appeloffre ;



}
