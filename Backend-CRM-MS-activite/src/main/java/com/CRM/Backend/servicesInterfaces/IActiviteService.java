package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.activite;

import java.util.Date;
import java.util.List;

public interface IActiviteService {

    List<activite> Allactivites();
    public activite retrieveactivite(Long id);

    activite addactivite(activite activite);

    // void deleteactivite(Long id);

    activite updateactivite(activite activite);

    activite statusActiviteENCOURS(Long id);

    activite statusActiviteTERMINE(Long id);

    activite notesActivite(Long id, String notes);


    List<activite> AllactivitesStatus(String status);

    List<activite> AllactivitesType(String type);

    activite relationActiviteOpportunite(Long id);

    activite relationActiviteTicket(Long id);

    activite affecteCommercial(Long id, Long idComm);

    activite setActiviteInactif(Long id);

    List<activite> inactifActivites();

    List<activite> actifActivites();

    List<activite> getActivitesForOpportunite(Long idOpportunite);

    List<activite> getActivitesForTicket(Long idTicket);
}
