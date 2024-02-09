package com.CRM.Backend.entities.Dto;

import com.CRM.Backend.entities.typeTicket;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class DtoTicketChangeType {


	Long idTicket;

	@Enumerated(EnumType.STRING)
	com.CRM.Backend.entities.typeTicket typeTicket;

	Long idModificateur;



}
