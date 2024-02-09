package com.CRM.Backend.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.*;
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
public abstract class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long idUser;
	
	String image;
	String mail;
    String numTel;
    String adresse;

    @Temporal(TemporalType.DATE)
    Date dateCreation;

    @Temporal(TemporalType.DATE)
    Date dateModification;

	
    @Enumerated(EnumType.STRING)
    StatusUser statusUser;
}
