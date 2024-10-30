package com.CRM.Backend.services;

import com.CRM.Backend.entities.ClientEntity;
import com.CRM.Backend.entities.Commande;
import com.CRM.Backend.entities.Reclamation;
import com.CRM.Backend.entities.status;
import com.CRM.Backend.repositories.CommandeRepository;
import com.CRM.Backend.repositories.ReclamationRepository;
import com.CRM.Backend.servicesInterfaces.ClientServiceFeignClient;
import com.CRM.Backend.servicesInterfaces.IReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class iReclamationServiceImp implements IReclamationService {
    @Autowired
    private ReclamationRepository reclamationRepository;

    @Autowired
    private ClientServiceFeignClient clientServiceFeignClient;
    @Autowired
    CommandeRepository commandeRepository;

    @Override
    public Reclamation createrec( Reclamation r , Long idcmd) {
        Commande c = commandeRepository.findByIdC(idcmd);

        ClientEntity clientDetails = (ClientEntity) clientServiceFeignClient.getClientDetails(c.getIdClient());

        r.setIdcmd(idcmd);
        r.setIdclt(clientDetails.getIdClient());
        r.setStatus(status.en_cours_de_traitement);
        r.setNomcleint(clientDetails.getNomEntreprise());
        r.setIdtese(c.getIdetse());
        return reclamationRepository.save(r);
    }

    @Override
    public List<Reclamation> getReclamationByidcmd(Long idcmd) {
        return   reclamationRepository.findAllByIdcmdIs(idcmd);

    }

    @Override
    public List<Reclamation> getReclamationByidclt(Long idclt) {
return   reclamationRepository.findAllByIdcltIs (idclt);
    }

    @Override
    public List<Reclamation> getReclamationByidetse(Long etse) {
        return   reclamationRepository.findAllByidtese(etse);

    }

    @Override
    public Reclamation getReclamationById(Long idr) {
        return reclamationRepository.findById(idr).get()  ;
    }

    @Override
    public Reclamation updatereclmation(Reclamation R, Long idr ) {
     Reclamation r = reclamationRepository.findById(idr).get();
    r.setDateTraitement( LocalDateTime.now());
    r.setReponse( R.getReponse());
    r.setStatus(status.traité);
    return reclamationRepository.save(r);
    }
}
