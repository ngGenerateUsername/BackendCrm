package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Appeloffre;
import com.CRM.Backend.entities.etatAO;

import java.util.List;

public interface IAOService {
    public Appeloffre AddAppeloff(Appeloffre appeloffre , Long idproduit);
    public List<Appeloffre> getllAo();
    public Appeloffre findByIdproduitAndEtat  (Long idproduit);



}
