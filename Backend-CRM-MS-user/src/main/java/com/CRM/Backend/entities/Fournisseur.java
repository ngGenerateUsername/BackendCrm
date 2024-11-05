package com.CRM.Backend.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Fournisseur   extends  User  {
    String nomFournisseur;
    String CA;
    String Domaine;
    String Description;
    Long numFiscal;

}
