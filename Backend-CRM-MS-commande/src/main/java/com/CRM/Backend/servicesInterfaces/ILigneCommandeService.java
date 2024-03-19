package com.CRM.Backend.servicesInterfaces;


import com.CRM.Backend.entities.Dto.DTOLigneCommande;
import com.CRM.Backend.entities.LigneCommande;

import java.util.List;

public interface ILigneCommandeService {
    public String addLigneCommande(DTOLigneCommande dtoLigneCommande, Long idProduit);
    public List<LigneCommande> getAllLigneCommande();
    public LigneCommande getById(Long idLigneCommande );
    public void removeLigneCommande(Long idLigneCommande );
    public LigneCommande updateLigneCommande(LigneCommande ligneCommande);
}
