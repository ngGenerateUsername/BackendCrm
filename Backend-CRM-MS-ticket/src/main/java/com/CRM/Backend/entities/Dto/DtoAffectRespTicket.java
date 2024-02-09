package com.CRM.Backend.entities.Dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class DtoAffectRespTicket{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idTicket;

    Long idResponsable;

    Long idModificateur;



}
