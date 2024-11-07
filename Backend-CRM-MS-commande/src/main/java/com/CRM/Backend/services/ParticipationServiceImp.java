package com.CRM.Backend.services;

import com.CRM.Backend.entities.Appeloffre;
import com.CRM.Backend.entities.Categorie;
import com.CRM.Backend.entities.Dto.DTOCategorie;
import com.CRM.Backend.entities.Participation;
import com.CRM.Backend.entities.Produit;
import com.CRM.Backend.repositories.AORepository;
import com.CRM.Backend.repositories.CategorieRepository;
import com.CRM.Backend.repositories.ParticipationRepository;
import com.CRM.Backend.repositories.ProduitRepository;
import com.CRM.Backend.servicesInterfaces.ICategorieService;
import com.CRM.Backend.servicesInterfaces.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ParticipationServiceImp implements ParticipationService {
    @Autowired
    AORepository aoRepository;

    @Autowired
    ParticipationRepository participationRepository;
    @Override
    public Participation participate(Participation p, Long idf, Long idao) {
        p.setIdFournisseur(idf);
        p.setPrix(p.getPrix());
        p.setDatesoummision(p.getDatesoummision());
         p.setAppeloffre(aoRepository.findById(idao).get());

        participationRepository.save(p);
         return p;

    }
}
