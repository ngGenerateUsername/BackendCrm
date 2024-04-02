package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Commande;
import com.CRM.Backend.entities.Dto.DTOCommande;

import java.util.List;

public interface ICommandeService {
    public String addCommande(DTOCommande  dtoCommande, Long idcontact);
    public List<Commande> getAllCommande();
    public Commande getById(Long idC);
    public void removeCommande(Long Commande);
    public Commande updateCommande(Commande commande);
    public List<Commande> getAllCommandebyuser(Long idContact );

}
