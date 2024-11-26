package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Appeloffre;
import com.CRM.Backend.entities.Participation;
import com.CRM.Backend.entities.Participationdetail;

import java.util.List;

public interface IAOService {
    public Appeloffre AddAppeloff(Appeloffre appeloffre , Long idproduit);
    public List<Appeloffre> getllAo();
    public Appeloffre findByIdproduitAndEtat  (Long idproduit);
    public Appeloffre findbyid  (Long idappeloffre);
    public void  deleteappeloffre (Long idappeloffre);

    public List<Appeloffre> findbyidetse (Long idetse);
    public List<Participationdetail> participant(Long idao);



}
