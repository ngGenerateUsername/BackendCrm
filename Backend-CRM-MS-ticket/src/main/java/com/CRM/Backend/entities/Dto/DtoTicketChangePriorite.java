package com.CRM.Backend.entities.Dto;

import com.CRM.Backend.entities.prioriteTicket;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class DtoTicketChangePriorite {


	Long idTicket;

	@Enumerated(EnumType.STRING)
	com.CRM.Backend.entities.prioriteTicket prioriteTicket;

	Long idModificateur;



}
