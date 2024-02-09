package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Entreprise;
import com.CRM.Backend.entities.dto.DtoEntreprise;

import java.util.List;


public interface IEntrepriseService {
    public List<Entreprise> retrieveAllentreprises();
    public Entreprise ajoutEentreprise(Entreprise e);
    public Entreprise EentrepriseDetails(Long id);
    public Entreprise updateEntreprise(DtoEntreprise cc);
}
