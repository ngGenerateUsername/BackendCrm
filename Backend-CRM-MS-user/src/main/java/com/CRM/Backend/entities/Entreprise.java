package com.CRM.Backend.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@ToString
@SuperBuilder
@Table(name="entreprise")
public class Entreprise extends User {
    
    String nomEntreprise;
    String CA;
    String Domaine;
    String Description;
    Long numFiscal;
}
