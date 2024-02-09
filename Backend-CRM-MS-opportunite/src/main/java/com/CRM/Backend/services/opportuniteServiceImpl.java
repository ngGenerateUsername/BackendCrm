package com.CRM.Backend.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.repositories.OpportuniteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRM.Backend.servicesInterfaces.IOpportuniteService;

import static com.CRM.Backend.entities.statusActif.*;
import static com.CRM.Backend.entities.statusOpportunite.*;

@Service
public class opportuniteServiceImpl implements IOpportuniteService {
	@Autowired
	OpportuniteRepository opportuniteRepository;

	public opportunite addopportunite(opportunite opportunite) {
		try {
			com.CRM.Backend.entities.opportunite opportunitefinal = new opportunite();
			opportunitefinal.setTitre(opportunite.getTitre());
			opportunitefinal.setStatusOpportunite(PISTE);
			opportunitefinal.setPourcentage(opportunite.getPourcentage());
			opportunitefinal.setDateDeFermeturePrevue(opportunite.getDateDeFermeturePrevue());
			opportunitefinal.setEmail(opportunite.getEmail());
			opportunitefinal.setTelephone(opportunite.getTelephone());
			opportunitefinal.setNotes(opportunite.getNotes());
			opportunitefinal.setNomSocieteContact(opportunite.getNomSocieteContact());
			opportunitefinal.setNomContact(opportunite.getNomContact());
			opportunitefinal.setImagecontact(opportunite.getImagecontact());
			opportunitefinal.setPrioriteOpportunite(opportunite.getPrioriteOpportunite());
			opportunitefinal.setNomCommercial(opportunite.getNomCommercial());
			opportunitefinal.setImageComercial(opportunite.getImageComercial());
			opportunitefinal.setRevenuespere(opportunite.getRevenuespere());
			opportunitefinal.setIdCreateur(opportunite.getIdCreateur());
			opportunitefinal.setIdCommercial(opportunite.getIdCommercial());
			opportunitefinal.setIdEntreprise(opportunite.getIdEntreprise());
			opportunitefinal.setDateCreation(new Date());
			opportunitefinal.setIdClient(opportunite.getIdClient());
			opportunitefinal.setIdContact(opportunite.getIdContact());
			opportunitefinal.setNomEntreprise(opportunite.getNomEntreprise());
			opportunitefinal.setStatusActif(Actif);
			opportunite d = opportuniteRepository.save(opportunitefinal);
			return d;
		} catch (Exception e) {
			System.out.println("erreur domain add : " + e.getMessage());
			return null;
		}
	}

	public void deleteopportunite(Long id) {
		try {
			opportuniteRepository.deleteById(id);
		} catch (Exception e) {
			System.out.println("erreur domain delete : " + id);
		}
	}

	public opportunite updateopportunite(opportunite opportunite) {
		try {
			opportunite d = opportuniteRepository.save(opportunite);
			return d;
		} catch (Exception e) {
			System.out.println("erreur domain update : " + e.getMessage());
			return null;
		}
	}

	public opportunite retrieveopportunite(Long id) {
		try {
			opportunite d = opportuniteRepository.findById(id).orElse(null);
			return d;
		} catch (Exception e) {
			// log.info("erreur domain retrieve : "+e.getMessage());
			return null;
		}
	}

	public List<opportunite> Allopportunites() {
		List<opportunite> domains = opportuniteRepository.findAll();
		return domains;
	}

	public List<opportunite> AllopportunitesPISTE() {
		List<opportunite> pisteActif = new ArrayList<>();
		String pisteStatus = "PISTE";
		String actifStatus = "Actif";

		List<opportunite> allOpportunites = opportuniteRepository.findAll();
		for (opportunite opportunitee : allOpportunites) {
			if (opportunitee.getStatusOpportunite().toString().equals(pisteStatus)
					&& opportunitee.getStatusActif().toString().equals(actifStatus)) {
				pisteActif.add(opportunitee);
			}
		}
		return pisteActif;
	}
	public List<opportunite> AllopportunitesPOTENTIEL() {
		List<opportunite> potActif = new ArrayList<>();
		String potentielStatus = "POTENTIEL";
		String actifStatus = "Actif";

		List<opportunite> allOpportunites = opportuniteRepository.findAll();
		for (opportunite opportunitee : allOpportunites) {
			if (opportunitee.getStatusOpportunite().toString().equals(potentielStatus)
					&& opportunitee.getStatusActif().toString().equals(actifStatus)) {
				potActif.add(opportunitee);
			}
		}
		return potActif;
	}

	public List<opportunite> AllopportunitesCONFIRMEE() {
		List<opportunite> confActif = new ArrayList<>();
		String confirmeeStatus = "CONFIRMEE";
		String actifStatus = "Actif";

		List<opportunite> allOpportunites = opportuniteRepository.findAll();
		for (opportunite opportunitee : allOpportunites) {
			if (opportunitee.getStatusOpportunite().toString().equals(confirmeeStatus)
					&& opportunitee.getStatusActif().toString().equals(actifStatus)) {
				confActif.add(opportunitee);
			}
		}
		return confActif;
	}
	public List<opportunite> AllopportunitesSIGNEE() {
		List<opportunite> signeeActif = new ArrayList<>();
		String signeeStatus = "SIGNEE";
		String actifStatus = "Actif";

		List<opportunite> allOpportunites = opportuniteRepository.findAll();
		for (opportunite opportunitee : allOpportunites) {
			if (opportunitee.getStatusOpportunite().toString().equals(signeeStatus)
					&& opportunitee.getStatusActif().toString().equals(actifStatus)) {
				signeeActif.add(opportunitee);
			}
		}
		return signeeActif;
	}
	public List<opportunite> AllopportunitesPERDUE() {
		List<opportunite> perdueActif = new ArrayList<>();
		String perdueStatus = "PERDUE";
		String actifStatus = "Actif";

		List<opportunite> allOpportunites = opportuniteRepository.findAll();
		for (opportunite opportunitee : allOpportunites) {
			if (opportunitee.getStatusOpportunite().toString().equals(perdueStatus)
					&& opportunitee.getStatusActif().toString().equals(actifStatus)) {
				perdueActif.add(opportunitee);
			}
		}
		return perdueActif;
	}

	@Override
	public opportunite statusOpportunitePOTENTIEL(Long id) {
		opportunite opportunite=retrieveopportunite(id);
		opportunite.setDatePOTENTIEL(new Date());
		opportunite.setStatusOpportunite(POTENTIEL);
		opportunite.setDateModification(new Date());
		return opportuniteRepository.save(opportunite);
	}

	@Override
	public opportunite statusOpportunitePISTE(Long id) {
		opportunite opportunite=retrieveopportunite(id);
		opportunite.setDatePISTE(new Date());
		opportunite.setStatusOpportunite(PISTE);
		opportunite.setDateModification(new Date());
		return opportuniteRepository.save(opportunite);
	}

	@Override
	public opportunite statusOpportuniteCONFIRMEE(Long id) {
		opportunite opportunite=retrieveopportunite(id);
		opportunite.setDateCONFIRMEE(new Date());
		opportunite.setStatusOpportunite(CONFIRMEE);
		opportunite.setDateModification(new Date());
		return opportuniteRepository.save(opportunite);
	}

	@Override
	public opportunite statusOpportunitePERDUE(Long id) {
		opportunite opportunite=retrieveopportunite(id);
		opportunite.setDatePERDUE(new Date());
		opportunite.setStatusOpportunite(statusOpportunite.PERDUE);
		opportunite.setDateModification(new Date());
		return opportuniteRepository.save(opportunite);
	}

	@Override
	public opportunite statusOpportuniteSIGNEE(Long id) {
		opportunite opportunite=retrieveopportunite(id);
		opportunite.setDateSIGNEE(new Date());
		opportunite.setStatusOpportunite(statusOpportunite.SIGNEE);
		 opportunite.setDateModification(new Date());
		return opportuniteRepository.save(opportunite);
	}
	@Override
	public opportunite updatePriorite(Long id, prioriteOpportunite prioriteOpp) {
		opportunite opportunite = retrieveopportunite(id);
		opportunite.setPrioriteOpportunite(prioriteOpp);
		return opportuniteRepository.save(opportunite);
	}

	@Override
	public List<opportunite> inactifOpportunities() {
		List<opportunite> opportunites=opportuniteRepository.findAll();
		List<opportunite> statusActif = new ArrayList<>();
		String inactif="Inactif";
		;
		for (opportunite opportunitee : opportunites) {

			if(opportunitee.getStatusActif().toString().equals(inactif)){
				statusActif.add(opportunitee);
			}
		}
		return statusActif;
	}

	@Override
	public List<opportunite> actifOpportunities() {
		List<opportunite> opportunites=opportuniteRepository.findAll();
		List<opportunite> statusActif = new ArrayList<>();
		String actif="Actif";

		;
		for (opportunite opportunitee : opportunites) {

			if(opportunitee.getStatusActif().toString().equals(actif)){
				statusActif.add(opportunitee);
			}
		}
		return statusActif;
	}

	@Override
	public opportunite deleteOpportunite(Long id) {
		opportunite opportunite=retrieveopportunite(id);
		opportunite.setStatusActif(Inactif);
		return opportuniteRepository.save(opportunite);
	}
}
