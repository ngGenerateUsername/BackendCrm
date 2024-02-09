package com.CRM.Backend.entities.dto;

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
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@ToString
@SuperBuilder
@Table(name="Client")
public class DtoClient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idClient;
    String nomEntreprise;
    String CA;
    String Domaine;
    String Description;
    String numFiscal;
    String mail;
    String numTel;
    String adresse;

    @Temporal(TemporalType.DATE)
    Date dateCreation;

}
