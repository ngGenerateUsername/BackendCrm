package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Offre;

public interface IOffreService {
    public Offre getById(Integer idOffre);
    public Offre addOffre(Offre offre);
    public void removeOffre(Integer idOffre);
}
