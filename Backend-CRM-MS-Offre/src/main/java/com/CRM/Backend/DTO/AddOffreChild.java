package com.CRM.Backend.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AddOffreChild {

    private Long idProduit;
    private Long qte;
    private double remise;
    private double prixHT;
    private double prixHTTC;
    
}
