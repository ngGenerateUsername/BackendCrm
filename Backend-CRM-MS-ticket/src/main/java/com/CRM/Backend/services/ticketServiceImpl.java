package com.CRM.Backend.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.entities.Dto.*;
import com.CRM.Backend.repositories.ticketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRM.Backend.servicesInterfaces.ITicketService;

@Service
public class ticketServiceImpl implements ITicketService{
    @Autowired
	ticketRepository ticketRepository;

	public ticket addticket(DtoTicket ticket) {
		try {
             com.CRM.Backend.entities.ticket ticketfinal = new ticket();
             ticketfinal.setTitre(ticket.getTitre());
             ticketfinal.setDescription(ticket.getDescription());
             ticketfinal.setDateCreation(new Date());
             ticketfinal.setStatusTicket(statusTicket.ATTENTE);
             ticketfinal.setPrioriteTicket(ticket.getPrioriteTicket());
             ticketfinal.setTypeTicket(ticket.getTypeTicket());

             ticketfinal.setIdClient(ticket.getIdClient());
             ticketfinal.setIdCreateur(ticket.getIdCreateur());
             ticketfinal.setIdResponsable(ticket.getIdResponsable());
             ticketfinal.setIdEntreprise(ticket.getIdEntreprise());
			ticket d=ticketRepository.save(ticketfinal);
			return d;
		}
		catch(Exception e) {
			System.out.println("erreur domain add : "+e.getMessage());
			return null;
		}
	}

	public void deleteticket(Long id) {
		try {
			ticketRepository.deleteById(id);
		}
		catch(Exception e) {
			System.out.println("erreur domain delete : "+id);
		}
	}

	public ticket updateticket(ticket ticket) {
		try {
			ticket d=ticketRepository.save(ticket);
			return d;
		}
		catch(Exception e) {
			System.out.println("erreur domain update : "+e.getMessage());
			return null;
		}
	}

	public ticket retrieveticket(Long id) {
		try {
			ticket d=ticketRepository.findById(id).orElse(null);
			return d;
		}
		catch(Exception e) {
			//log.info("erreur domain retrieve : "+e.getMessage());
			return null;
		}
	}

	public ticket SetstatusTicket(DtoTicketChangeStatus DtoTicketChangeStatus) {
		ticket ticket=retrieveticket(DtoTicketChangeStatus.getIdTicket());
		ticket.setDateModification(new Date());
		ticket.setIdModificateur(DtoTicketChangeStatus.getIdModificateur());

		if(DtoTicketChangeStatus.getStatusTicket().equals(statusTicket.ATTENTE))
		{
			ticket.setStatusTicket(statusTicket.ATTENTE);
			ticket.setDateCreation(new Date());
		}
		if(DtoTicketChangeStatus.getStatusTicket().equals(statusTicket.IDENTIFICATION))
		{
			ticket.setStatusTicket(statusTicket.IDENTIFICATION);
			ticket.setDateTraitement(new Date());
		}
		if(DtoTicketChangeStatus.getStatusTicket().equals(statusTicket.FERME_ECHEC))
		{
			ticket.setStatusTicket(statusTicket.FERME_ECHEC);
			ticket.setDateRefus(new Date());
		}
		if(DtoTicketChangeStatus.getStatusTicket().equals(statusTicket.FERME_RESOLU))
		{
			ticket.setStatusTicket(statusTicket.FERME_RESOLU);
			ticket.setDateAccepte(new Date());
		}
		return ticketRepository.save(ticket);
		}

	public ticket notesTicket(DtoNotesTicket DtoNotesTicket) {
		ticket ticket=retrieveticket(DtoNotesTicket.getIdTicket());
		ticket.setNotes(DtoNotesTicket.getNotes());
		ticket.setIdModificateur(DtoNotesTicket.getIdModificateur());
		ticket.setDateModification(new Date());
		return ticketRepository.save(ticket);
	}

	public ticket SetprioriteTicket(DtoTicketChangePriorite DtoTicketChangePriorite) {
		ticket ticket=retrieveticket(DtoTicketChangePriorite.getIdTicket());
		ticket.setDateModification(new Date());
		ticket.setIdModificateur(DtoTicketChangePriorite.getIdModificateur());
		ticket.setPrioriteTicket(DtoTicketChangePriorite.getPrioriteTicket());
		return ticketRepository.save(ticket);
	}
	public ticket SetTypeTicket(DtoTicketChangeType DtoTicketChangeType) {
		ticket ticket=retrieveticket(DtoTicketChangeType.getIdTicket());
		ticket.setDateModification(new Date());
		ticket.setIdModificateur(DtoTicketChangeType.getIdModificateur());
		ticket.setTypeTicket(DtoTicketChangeType.getTypeTicket());
		return ticketRepository.save(ticket);
	}

	public ticket affecteResponsable(DtoAffectRespTicket DtoAffectRespTicket) {
		ticket ticket=retrieveticket(DtoAffectRespTicket.getIdTicket());
		ticket.setIdResponsable(DtoAffectRespTicket.getIdResponsable());
		ticket.setIdModificateur(DtoAffectRespTicket.getIdModificateur());
		ticket.setDateModification(new Date());
		return ticketRepository.save(ticket);
	}

	public List<ticket> Alltickets() {
		List<ticket> domains=ticketRepository.findAll();
		return domains;
	}

	public List<ticket> AllticketsStatus(String status) {
		List<ticket> tickets=ticketRepository.findAll();
		List<ticket> ticketsStatus = new ArrayList<>();
		for (ticket tickett : tickets) {

			if(tickett.getStatusTicket().toString().equals(status)){
				ticketsStatus.add(tickett);
			}
		}

		return ticketsStatus;
	}

	public List<ticket> AllticketsType(String type) {
		List<ticket> tickets=ticketRepository.findAll();
		List<ticket> ticketsType = new ArrayList<>();
		for (ticket tickett : tickets) {

			if(tickett.getTypeTicket().toString() != null && tickett.getTypeTicket().equals(type)) {
				ticketsType.add(tickett);	
			}
		}
		return ticketsType;
	}

	public List<ticket> AllticketsPerContact(Long idCreateur) {
		List<ticket> tickets=ticketRepository.findAll();
		List<ticket> ticketsList = new ArrayList<>();
		for (ticket tickett : tickets) {
			System.out.println(tickett.getIdCreateur());
			if(tickett.getIdCreateur() != null && tickett.getIdCreateur().equals(idCreateur)){
				ticketsList.add(tickett);
			}
		}
		return ticketsList;
	}

	public List<ticket> AllticketsPerEntreprise(Long idEntreprise) {
		List<ticket> tickets=ticketRepository.findAll();
		List<ticket> ticketsList = new ArrayList<>();
		for (ticket tickett : tickets) {
			if(tickett.getIdEntreprise() != null && tickett.getIdEntreprise().equals(idEntreprise)){
				ticketsList.add(tickett);
			}
		}
		return ticketsList;
	}

	public List<ticket> AllticketsPerRespTicket(Long idResponsable) {
		List<ticket> tickets=ticketRepository.findAll();
		List<ticket> ticketsList = new ArrayList<>();
		for (ticket tickett : tickets) {
			if(tickett.getIdResponsable() != null && tickett.getIdResponsable().equals(idResponsable)){
				ticketsList.add(tickett);
			}
		}
		return ticketsList;
	}

	public List<ticket> AllticketsPerClient(Long idResponsable) {
		List<ticket> tickets=ticketRepository.findAll();
		List<ticket> ticketsList = new ArrayList<>();
		for (ticket tickett : tickets) {
			if(tickett.getIdResponsable() != null && tickett.getIdResponsable().equals(idResponsable) ){
				ticketsList.add(tickett);
			}
		}
		return ticketsList;
	}
}
