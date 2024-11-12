package com.CRM.Backend.entities.Dto;

import com.CRM.Backend.entities.LigneFacture;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Setter
@Getter
@Builder
public class DTOFacture {
    private Long id;
    private String Titre;
    private LocalDateTime dateCreation;
    private double montantTotalHT;
    private double montantTotalHTTC;
    Long idClient;
    Long idCommercial;
    private boolean timbre;
    private List<LigneFacture> lignes;



}
