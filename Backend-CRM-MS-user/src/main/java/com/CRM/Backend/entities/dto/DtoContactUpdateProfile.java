package com.CRM.Backend.entities.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DtoContactUpdateProfile{

    Long idUser;
    String nom;
    String prenom;
    String numTel;
    String adresse;


}
