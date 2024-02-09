package com.CRM.Backend.entities.dto;

import com.CRM.Backend.entities.StatusUser;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class DtoUserChangeStatus{

	Long idUser;
	
    @Enumerated(EnumType.STRING)
    StatusUser statusUser;
}
