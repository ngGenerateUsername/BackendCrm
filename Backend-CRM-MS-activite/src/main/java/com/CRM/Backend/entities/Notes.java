package com.CRM.Backend.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class Notes implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idNote;
    String Description;
    String nomCreateur;
    String ImageCreateur;
    @Enumerated(EnumType.STRING)
    typeNotes typeNotes;

    @Temporal(TemporalType.TIMESTAMP)
    Date dateCreation;


    Long idActivite;
    Long idOpportunite;
    Long idContrat;
    Long idCreateur;
    Long idModificateur;





}
