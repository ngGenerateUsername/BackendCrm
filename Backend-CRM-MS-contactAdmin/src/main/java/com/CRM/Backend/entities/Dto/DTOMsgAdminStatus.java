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
@ToString
public class DTOMsgAdminStatus implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idMsg;

    @Enumerated(EnumType.STRING)
    com.CRM.Backend.entities.statusMsg statusMsg;




}
