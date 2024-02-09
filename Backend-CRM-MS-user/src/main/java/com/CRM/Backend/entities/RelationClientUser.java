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
@Entity
@ToString
@Builder
@Table(name="RelationClientUser")
public class RelationClientUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long idRelationClientUser;

    Long idClient;

    Long idUser;

    Long idEntreprise;

    @Temporal(TemporalType.DATE)
    Date dateCreation;

    @Enumerated(EnumType.STRING)
    TypeRelationClientUser TypeRelationClientUser;





}



