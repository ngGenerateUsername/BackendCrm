package com.CRM.Backend.entities;

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
public class RelationClientUserDTO {


    Long idRelationClientUser;

    Client Client;

    Contact User;

    Entreprise Entreprise;







}



