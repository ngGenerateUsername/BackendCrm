package com.CRM.Backend.entities.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class DtoEntreprise {

    Long idUser;
    String nomEntreprise;
    String CA;
    String Domaine;
    String Description;
    Long numFiscal;
    String mail;
    String numTel;
    String adresse;

    @Temporal(TemporalType.DATE)
    Date dateCreation;
}
