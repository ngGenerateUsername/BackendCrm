package com.CRM.Backend.entities;

import lombok.*;

import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Fournisseur
{    @Id
private Long idUser;
    String nomFournisseur;
    String CA;
    String Domaine;
    String Description;
    Long numFiscal;
    String adresse;
  String domaine;
    String mail;


}
