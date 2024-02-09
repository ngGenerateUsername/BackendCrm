package com.CRM.Backend.entities.Dto;

import com.CRM.Backend.entities.prioriteTicket;
import com.CRM.Backend.entities.statusTicket;
import com.CRM.Backend.entities.typeTicket;
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
public class DtoTicket{

	String titre;
	String description;

	@Enumerated(EnumType.STRING)
    com.CRM.Backend.entities.typeTicket typeTicket;
	
    @Enumerated(EnumType.STRING)
    com.CRM.Backend.entities.statusTicket statusTicket;
   
    @Enumerated(EnumType.STRING)
    com.CRM.Backend.entities.prioriteTicket prioriteTicket;

    Long idCreateur;
    Long idEntreprise;
    Long idResponsable;
    Long idClient;
    Long idModificateur;



}
