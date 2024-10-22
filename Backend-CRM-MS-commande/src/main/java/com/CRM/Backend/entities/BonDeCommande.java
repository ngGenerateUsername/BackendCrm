package com.CRM.Backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class BonDeCommande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idbdc;
    @CreationTimestamp
    @Column(updatable = false,nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime DateLivraison ;   ;
    private String NomClient;
    private String AdressLivraison;
    private  Long price;
    private String nomentreprise;
    private Long idcmd;
    private Long idetse;
}
