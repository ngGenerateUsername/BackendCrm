package com.CRM.Backend.services;

import com.CRM.Backend.entities.Entreprise;
import com.CRM.Backend.entities.Fournisseur;
import com.CRM.Backend.entities.RoleEntreprise;
import com.CRM.Backend.entities.StatusUser;
import com.CRM.Backend.repositories.FournisseurRepository;
import com.CRM.Backend.repositories.RoleEntrepriseRepository;
import com.CRM.Backend.servicesInterfaces.IFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class FournisseurServiceImpl implements IFournisseurService {
   
	@Autowired
	FournisseurRepository FournisseurRepository;

	@Autowired
	RoleEntrepriseRepository roleEntrepriseRepository;


	public List<Fournisseur> retrieveAllFournisseurs() {
		return FournisseurRepository.findAll();
	}

    public Fournisseur ajoutFournisseur(Fournisseur e) {
		e.setStatusUser(StatusUser.ACTIF);
		e.setImage("company.png");
		return FournisseurRepository.save(e);
	}	

	public Fournisseur updateFournisseur(Fournisseur cc) {
		try {
			Fournisseur ccc = FournisseurRepository.findById(cc.getIdUser()).get();

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
			if(cc.getNomFournisseur() != null){
				ccc.setNomFournisseur(cc.getNomFournisseur());
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
			return FournisseurRepository.save(ccc);
		}
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public Fournisseur retrieveFournissuerPercontatc(Long id) {
		List<RoleEntreprise>  role_entreprises = roleEntrepriseRepository.findAll();
		System.out.println(role_entreprises);
		for (RoleEntreprise roleEntreprise : role_entreprises) {
			if(roleEntreprise.getIdContact().equals(id))
				return FournisseurRepository.findById(roleEntreprise.getIdFournisseur() ).get();
		}
		return new Fournisseur();
	}



	public Fournisseur FournisseurDetails(Long id) {
		return FournisseurRepository.findById(id).get();
	}
}
