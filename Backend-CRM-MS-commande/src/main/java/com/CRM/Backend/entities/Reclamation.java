package com.CRM.Backend.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Reclamation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idrec;

     private Long idcmd ;
private Long idclt;
    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime dateCreation = LocalDateTime.now();

    @Column( nullable = true)
    private LocalDateTime dateTraitement  ;
    @Enumerated(EnumType.STRING)
    private status status;
private String description;
private String reponse ;
private Long idcomer;
private String nomcleint;

}
