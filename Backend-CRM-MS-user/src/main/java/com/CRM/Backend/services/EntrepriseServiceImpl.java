package com.CRM.Backend.services;

import java.util.Date;
import java.util.List;

import com.CRM.Backend.entities.Entreprise;
import com.CRM.Backend.entities.StatusUser;
import com.CRM.Backend.entities.dto.DtoEntreprise;
import com.CRM.Backend.repositories.EntrepriseRepository;
import com.CRM.Backend.servicesInterfaces.IEntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EntrepriseServiceImpl implements IEntrepriseService {
   
	@Autowired
	EntrepriseRepository entrepriseRepository;

    public List<Entreprise> retrieveAllentreprises() {
		return entrepriseRepository.findAll();
	}

    public Entreprise ajoutEentreprise(Entreprise e) {
		e.setStatusUser(StatusUser.ACTIF);
		e.setImage("company.png");
		return entrepriseRepository.save(e);
	}	

	public Entreprise updateEntreprise(DtoEntreprise cc) {
		try {
			Entreprise ccc = entrepriseRepository.findById(cc.getIdUser()).get();

			if(cc.getCA() != null){
				ccc.setCA(cc.getCA());
				ccc.setDateModification(new Date());
			}
			if(cc.getDomaine() != null){
				ccc.setDomaine(cc.getDomaine());
				ccc.setDateModification(new Date());
			}
			if(cc.getDescription() != null){
				ccc.setDescription(cc.getDescription());
				ccc.setDateModification(new Date());
			}
			if(cc.getNumFiscal() != null){
				ccc.setNumFiscal(cc.getNumFiscal());
				ccc.setDateModification(new Date());
			}
			if(cc.getNomEntreprise() != null){
				ccc.setNomEntreprise(cc.getNomEntreprise());
				ccc.setDateModification(new Date());
			}
			if(cc.getAdresse() != null){
				ccc.setAdresse(cc.getAdresse());
				ccc.setDateModification(new Date());
			}
			if(cc.getNumTel() != null){
				ccc.setNumTel(cc.getNumTel());
				ccc.setDateModification(new Date());
			}
			if(cc.getMail() != null){
				ccc.setMail(cc.getMail());
				ccc.setDateModification(new Date());
			}
			if(cc.getDateCreation() != null){
				ccc.setDateCreation(cc.getDateCreation());
				ccc.setDateModification(new Date());
			}
			return entrepriseRepository.save(ccc);
		}
		catch(Exception e) {
			return null;
		}
	}

    public Entreprise EentrepriseDetails(Long id) {
		return entrepriseRepository.findById(id).get();
	}
}
