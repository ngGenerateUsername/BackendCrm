package com.CRM.Backend.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
public class opportunite implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idOpportunite;
	
	String titre;
	Long revenuespere;
    Float pourcentage;
    String Email;

    String telephone;
    String notes;
    String nomSocieteContact;
    String nomContact;
    String imagecontact;
    String nomCommercial;
    String imageComercial;
    String nomEntreprise;


    @Temporal(TemporalType.DATE)
    Date dateDeFermeturePrevue;
    @Temporal(TemporalType.DATE)
    Date dateCreation;
    @Temporal(TemporalType.DATE)
    Date dateFerme;
    @Temporal(TemporalType.DATE)
    Date dateCONFIRMEE;

    @Temporal(TemporalType.DATE)
    Date datePERDUE;

    @Temporal(TemporalType.DATE)
    Date dateSIGNEE;
    @Temporal(TemporalType.DATE)
    Date dateModification;
    @Temporal(TemporalType.DATE)
    Date datePOTENTIEL;

    @Temporal(TemporalType.DATE)
    Date datePISTE;
    @Enumerated(EnumType.STRING)
    com.CRM.Backend.entities.statusOpportunite statusOpportunite;

    @Enumerated(EnumType.STRING)
    com.CRM.Backend.entities.langues langues;
   
    @Enumerated(EnumType.STRING)
    com.CRM.Backend.entities.prioriteOpportunite prioriteOpportunite;
    @Enumerated(EnumType.STRING)
    statusActif statusActif;

    Long idCommercial;
    Long idEntreprise;
    Long idModificateur;
    Long idCreateur;
    Long idClient;
    Long idContact;

    @OneToOne
    com.CRM.Backend.entities.contrat contrat;
}
