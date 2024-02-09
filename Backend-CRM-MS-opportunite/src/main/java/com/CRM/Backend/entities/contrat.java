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
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class contrat implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idContrat;

    String PDF;
    String Notes;

    @Temporal(TemporalType.DATE)
    Date dateSignature;

    @Enumerated(EnumType.STRING)
    statusContrat statusContrat;

    Long idClient;
    Long idEntreprise;
    Long idCommercial;
    Long idSignataire;
    Long idCreateur;


  //  @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL, orphanRemoval = true)
  //  Set<FileContrat> files;

    @OneToOne
    opportunite opportunite;
}
