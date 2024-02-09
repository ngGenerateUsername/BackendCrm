package com.CRM.Backend.services;

import com.CRM.Backend.entities.Offre;
import com.CRM.Backend.entities.Produit;
import com.CRM.Backend.repositories.OffreRepository;
import com.CRM.Backend.servicesInterfaces.IOffreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class IOffreServiceImp implements IOffreService {

    OffreRepository offreRepository;

    @Override
    public Offre getById(Integer idOffre) {
            return offreRepository.findById(idOffre).orElse(null);

    }

    @Override
    public Offre addOffre(Offre offre) {
        return offreRepository.save(offre);
    }

    @Override
    public void removeOffre(Integer idOffre) {
        offreRepository.deleteById(idOffre);
    }
}
