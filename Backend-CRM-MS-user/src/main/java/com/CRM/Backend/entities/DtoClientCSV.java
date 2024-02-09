package com.CRM.Backend.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class DtoClientCSV {

    String nom;
    String tel;
    String mail;
    String adresse;
    String domaine;
    String numero_didentification_fiscale;
    String chiffre_daffaires;

    @Temporal(TemporalType.DATE)
    Date date_de_lancement;

    String description;







}
