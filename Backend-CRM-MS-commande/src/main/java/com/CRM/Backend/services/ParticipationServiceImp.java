package com.CRM.Backend.services;

import com.CRM.Backend.entities.Fournisseur;
import com.CRM.Backend.entities.Participation;
import com.CRM.Backend.repositories.AORepository;
import com.CRM.Backend.repositories.ParticipationRepository;
import com.CRM.Backend.servicesInterfaces.FournisseurServiceFeignClient;
import com.CRM.Backend.servicesInterfaces.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ParticipationServiceImp implements ParticipationService {

    @Autowired
    FournisseurServiceFeignClient fournisseurServiceFeignClient;

    @Autowired
    AORepository aoRepository;

    @Autowired
    ParticipationRepository participationRepository;


    /*@Override
    public String participate(Participation p, Long idcf, Long idao) {
        String msg="test";
        Fournisseur f = fournisseurServiceFeignClient.FournisseurPerContact(idcf.longValue());
        boolean hasParticipated = false;
        List<Participation> participations = participationRepository.findByAppeloffre_Idao(idao);

        for (Participation participation : participations) {
            if (participation.getIdFournisseur().equals(f.getIdUser())) {
                hasParticipated = true;
                break;
            }
        }

        if (!hasParticipated) {

            p.setIdFournisseur(f.getIdUser());
            p.setPrix(p.getPrix());
            p.setDatesoummision(p.getDatesoummision());
            p.setAppeloffre(aoRepository.findById(idao).get());
            participationRepository.save(p);

            msg="particpation avec succeé";
        }
        else{
            msg="you have alreadu partipated";


      }

        return msg;

}*/


}
