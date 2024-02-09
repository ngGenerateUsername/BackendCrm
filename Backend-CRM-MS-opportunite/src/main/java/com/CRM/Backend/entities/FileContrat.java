package com.CRM.Backend.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class FileContrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idFile;
    Long idContrat;

    @Temporal(TemporalType.DATE)
    Date dateAjout;

    String Nom;
    String Taille;

   // @ManyToOne
   // @JoinColumn(name = "contrat_id")
   // contrat contrat;
}
