package com.CRM.Backend.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.CRM.Backend.entities.statusActivite;
import com.CRM.Backend.entities.typeActivite;
import com.CRM.Backend.entities.activite;
import com.CRM.Backend.entities.relationActivite;
import com.CRM.Backend.entities.statusActif;
import com.CRM.Backend.repositories.activiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRM.Backend.servicesInterfaces.IActiviteService;

@Service
public class activiteServiceImpl implements IActiviteService{
    @Autowired
	activiteRepository activiteRepository;
	
	@Override
	public List<activite> Allactivites() {
		List<activite> domains=activiteRepository.findAll();
		return domains;
	}


	@Override
	public activite addactivite(activite activite) {
		try {
			//activite.setStatusActivite(statusActivite.PLANIFIE);
			activite.setDateCreation(new Date());
			//activite.setDatePLANIFIE(new Date());
			activite.setStatusActif(statusActif.Actif);
			return activiteRepository.save(activite);
		} catch(Exception e) {
			//log.info("erreur domain add : "+e.getMessage());
			return null;
		}
	}


	@Override
	public activite setActiviteInactif(Long id) {
		activite activite=retrieveactivite(id);
		activite.setStatusActif(statusActif.Inactif);
		return activiteRepository.save(activite);
	}

	@Override
	public activite updateactivite(activite activite) {
		try {
			activite d=activiteRepository.save(activite);
			return d;
		}
		catch(Exception e) {
			//log.info("erreur domain update : "+e.getMessage());
			return null;
		}
	}

	public activite retrieveactivite(Long id) {
		try {
			activite d = activiteRepository.findById(id).orElse(null);
			return d;
		} catch (Exception e) {
			// log.info("erreur domain retrieve : "+e.getMessage());
			return null;
		}
	}

	@Override
	public activite statusActiviteENCOURS(Long id) {
		activite activite=retrieveactivite(id);
		activite.setDateENCOURS(new Date());
		activite.setStatusActivite(statusActivite.ENCOURS);
		activite.setDateModification(new Date());
		return activiteRepository.save(activite);
	}
	@Override
	public activite statusActiviteTERMINE(Long id) {
		activite activite=retrieveactivite(id);
		activite.setDateTERMINE(new Date());
		activite.setStatusActivite(statusActivite.TERMINE);
		activite.setDateModification(new Date());
		return activiteRepository.save(activite);
	}
	
	@Override
	public activite relationActiviteTicket(Long id) {
		activite activite=retrieveactivite(id);
		activite.setRelationActivite(relationActivite.Ticket);;
		return activiteRepository.save(activite);
	}

	@Override
	public activite relationActiviteOpportunite(Long id) {
		activite activite=retrieveactivite(id);
		activite.setRelationActivite(relationActivite.Opportunite);
		return activiteRepository.save(activite);
	}

	@Override
	public activite notesActivite(Long id,String notes) {
		activite activite=retrieveactivite(id);
		activite.setNotes(notes);
		activite.setDateModification(new Date());
		return activiteRepository.save(activite);
	}


	

	@Override
	public activite affecteCommercial(Long id,Long idComm) {
		activite activite=retrieveactivite(id);
		activite.setIdCommrcial(idComm);
		activite.setDateModification(new Date());
		return activiteRepository.save(activite);
	}

	@Override
	public List<activite> AllactivitesStatus(String status) {
		List<activite> activites=activiteRepository.findAll();
		List<activite> activitesStatus = new ArrayList<>();
		for (activite activitee : activites) {

			if(activitee.getStatusActivite().toString().equals(status)){
				activitesStatus.add(activitee);
			}
		}
		return activitesStatus;
	} 

	@Override
	public List<activite> inactifActivites() {
		List<activite> activites=activiteRepository.findAll();
		List<activite> statusActif = new ArrayList<>();
		String inactif="Inactif";
	
		 ;
		for (activite activitee : activites) {

			if(activitee.getStatusActif().toString().equals(inactif)){
				statusActif.add(activitee);
			}
		}
		return statusActif;
	}

	@Override
	public List<activite> actifActivites() {
		List<activite> activites=activiteRepository.findAll();
		List<activite> statusActif = new ArrayList<>();
		String actif="Actif";
	
		 ;
		for (activite activitee : activites) {

			if(activitee.getStatusActif().toString().equals(actif)){
				statusActif.add(activitee);
			}
		}
		return statusActif;
	}

	@Override
	public List<activite> AllactivitesType(String type) {
		List<activite> activites=activiteRepository.findAll();
		List<activite> activitesType = new ArrayList<>();
		for (activite activitee : activites) {

			if(activitee.getTypeActivite().toString().equals(type)){
				activitesType.add(activitee);	
			}
		}
		return activitesType;
	}
	@Override
	public List<activite> getActivitesForOpportunite(Long idOpportunite) {
		List<activite> activites = activiteRepository.findAll();
		List<activite> activitesTypes = new ArrayList<>();
		for (activite activitee : activites) {
			Long opportunitId = activitee.getIdOpportunite();
			if (opportunitId != null && opportunitId.equals(idOpportunite)) {
				activitesTypes.add(activitee);
			}
		}
		return activitesTypes;
	}

	@Override
	public List<activite> getActivitesForTicket(Long idTicket) {
		List<activite> activites = activiteRepository.findAll();
		List<activite> activitesTypes = new ArrayList<>();
		for (activite activitee : activites) {
			Long opportunitId = activitee.getIdTicket();
			if (opportunitId != null && opportunitId.equals(idTicket)) {
				activitesTypes.add(activitee);
			}
		}
		return activitesTypes;
	}
}
