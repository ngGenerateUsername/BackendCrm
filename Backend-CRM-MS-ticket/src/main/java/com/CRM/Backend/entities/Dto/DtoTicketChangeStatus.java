package com.CRM.Backend.entities.Dto;

import com.CRM.Backend.entities.statusTicket;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class DtoTicketChangeStatus{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idTicket;

    @Enumerated(EnumType.STRING)
	com.CRM.Backend.entities.statusTicket statusTicket;

	Long idModificateur;



}
