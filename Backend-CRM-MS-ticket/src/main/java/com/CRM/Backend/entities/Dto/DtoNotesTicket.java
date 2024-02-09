package com.CRM.Backend.entities.Dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class DtoNotesTicket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idTicket;

	String notes;

    Long idModificateur;



}
