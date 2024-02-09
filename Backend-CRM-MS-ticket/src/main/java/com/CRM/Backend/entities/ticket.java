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
public class ticket implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idTicket;
	
	String titre;
	String description;
    String notes;

    @Temporal(TemporalType.DATE)
    Date dateCreation;
    @Temporal(TemporalType.DATE)
    Date dateAccepte;
    @Temporal(TemporalType.DATE)
    Date dateRefus;
    @Temporal(TemporalType.DATE)
    Date dateTraitement;
    @Temporal(TemporalType.DATE)
    Date dateModification;

	
	@Enumerated(EnumType.STRING)
	typeTicket typeTicket;
	
    @Enumerated(EnumType.STRING)
	statusTicket statusTicket;
   
    @Enumerated(EnumType.STRING)
	prioriteTicket prioriteTicket;

    Long idCreateur;
    Long idEntreprise;
    Long idResponsable;
    Long idClient;
    Long idModificateur;



}
