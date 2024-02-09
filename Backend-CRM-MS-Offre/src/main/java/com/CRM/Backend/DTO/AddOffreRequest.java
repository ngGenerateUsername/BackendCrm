package com.CRM.Backend.DTO;

import java.util.List;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddOffreRequest {
    private Long idOffre;
    private String title;
    private double tvaMontant;
    private String TypeOffre;
    
    private double totalePrixHT;
    private double totalePrixHTTC;
    private Long idCommerciale;

    // @JsonIgnoreProperties(value = {"offre"})
    private List<AddOffreChild> listOffre;
}
