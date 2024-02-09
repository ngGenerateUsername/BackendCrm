package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.*;


import java.util.List;

public interface IOpportuniteService {


    public List<opportunite> Allopportunites();
    public opportunite retrieveopportunite(Long id);
    public opportunite addopportunite(opportunite opportunite);
    public opportunite statusOpportunitePISTE(Long id);
    public opportunite statusOpportunitePOTENTIEL(Long id);
    public opportunite statusOpportuniteCONFIRMEE(Long id);
    public opportunite statusOpportunitePERDUE(Long id);
    public opportunite statusOpportuniteSIGNEE(Long id);
    public opportunite updatePriorite(Long id, prioriteOpportunite prioriteOpp);

    List<opportunite> AllopportunitesPISTE();

    List<opportunite> AllopportunitesPOTENTIEL();

    List<opportunite> AllopportunitesCONFIRMEE();

    List<opportunite> AllopportunitesSIGNEE();

    List<opportunite> AllopportunitesPERDUE();

    List<opportunite> actifOpportunities();

    List<opportunite> inactifOpportunities();

    opportunite deleteOpportunite(Long id);
}
