package com.CRM.Backend.entities.Dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DTOMsgAdminCreate implements Serializable{

	
	String titre;
	String description;
    String mail;





}
