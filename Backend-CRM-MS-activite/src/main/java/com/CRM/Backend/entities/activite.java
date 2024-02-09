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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class activite implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idActivite;
	String titre;
    String notes;
    String Description;



    @Temporal(TemporalType.DATE)
    Date dateCreation;

    @Temporal(TemporalType.DATE)
    Date datePLANIFIE;
    @Temporal(TemporalType.DATE)
    Date dateENCOURS;
    @Temporal(TemporalType.DATE)
    Date dateTERMINE;
    @Temporal(TemporalType.DATE)
    Date dateDebut;
    @Temporal(TemporalType.DATE)
    Date dateFin;
    @Temporal(TemporalType.DATE)
    Date dateModification;

	
	@Enumerated(EnumType.STRING)
	typeActivite typeActivite;
	
    @Enumerated(EnumType.STRING)
        statusActivite statusActivite;
    @Enumerated(EnumType.STRING)
    prioriteActivite prioriteActivite;
    @Enumerated(EnumType.STRING)
	relationActivite relationActivite;
   
    @Enumerated(EnumType.STRING)
	statusActif statusActif;
   

    Long idOpportunite;
    Long idTicket;

    Long idClient;
    Long idCreateur;
    Long idModificateur;
    Long idCommrcial;
    Long idContact;




}
