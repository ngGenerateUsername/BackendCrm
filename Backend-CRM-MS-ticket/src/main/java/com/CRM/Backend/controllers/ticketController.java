package com.CRM.Backend.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.entities.Dto.*;
import com.CRM.Backend.repositories.ticketRepository;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import com.CRM.Backend.servicesInterfaces.ITicketService;


@RestController
@RequestMapping("/ticket")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ticketController {

	@Autowired
	private ticketRepository productRepository;

	@Autowired
	private SocketIOServer server;

	@Autowired
	ITicketService TicketService;


	@GetMapping("/AllTickets")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
	@ResponseBody
	public List<ticket> Alltickets() {
		return TicketService.Alltickets();
	}

	@GetMapping("/DetailsTicket")
	//@PreAuthorize("hasAuthority('[ROLE_ADMIN]')")
	@ResponseBody
	public ticket retrieveticket(@Param("id") Long id) {
		return TicketService.retrieveticket(id);
	}

	@PostMapping("/addTicket")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
	@ResponseBody
	public ticket addTicket(@RequestBody DtoTicket ticket) {
		return TicketService.addticket(ticket);
	}

	@DeleteMapping("/removeTicket/{ticket-id}")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
	@ResponseBody
	public void deleteTicket(@PathVariable("ticket-id") Long id) {
		TicketService.deleteticket(id);
	}


	@PutMapping("/setStatusTicket")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
	@ResponseBody
	public ticket setStatusTicket(@RequestBody DtoTicketChangeStatus DtoTicketChangeStatus) {
			ticket ticket = productRepository.findById(DtoTicketChangeStatus.getIdTicket()).get();

		server.getBroadcastOperations().sendEvent("ticket", ticket);
		return TicketService.SetstatusTicket(DtoTicketChangeStatus);
	}

	@PutMapping("/AddNotesTicket")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
	@ResponseBody
	public ticket notesTicket(@RequestBody DtoNotesTicket DtoNotesTicket) {
		return TicketService.notesTicket(DtoNotesTicket);
	}

	@PutMapping("/setPrioriteTicket")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
	@ResponseBody
	public ticket setPrioriteTicket(@RequestBody DtoTicketChangePriorite DtoTicketChangePriorite) {
		return TicketService.SetprioriteTicket(DtoTicketChangePriorite);
	}

	@PutMapping("/setTypeTicket")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
	@ResponseBody
	public ticket setTypeTicket(@RequestBody DtoTicketChangeType DtoTicketChangeType) {
		return TicketService.SetTypeTicket(DtoTicketChangeType);
	}

	@PutMapping("/affecteResponsable")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
	@ResponseBody
	public ticket affecteResponsable(@RequestBody DtoAffectRespTicket DtoAffectRespTicket) {
		return TicketService.affecteResponsable(DtoAffectRespTicket);
	}

	@GetMapping("/AllticketsStatus/{status}")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
	@ResponseBody
	public List<ticket> AllticketsStatus(@PathVariable("status") String status) {
		return TicketService.AllticketsStatus(status);
	}

	@GetMapping("/AllticketsType/{type}")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
	@ResponseBody
	public List<ticket> AllticketsType(@PathVariable("type") String type) {
		return TicketService.AllticketsType(type);
	}

	@GetMapping("/AllticketsPerRespTicket")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
	@ResponseBody
	public List<ticket> AllticketsPerRespTicket(@Param("id") Long id) {
		return TicketService.AllticketsPerRespTicket(id);
	}

	@GetMapping("/AllticketsPerClient")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
	@ResponseBody
	public List<ticket> AllticketsPerClient(@Param("id") Long id) {
		return TicketService.AllticketsPerClient(id);
	}

	@GetMapping("/AllticketsPerContact")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
	@ResponseBody
	public List<ticket> AllticketsPerContact(@Param("id") Long id) {
		return TicketService.AllticketsPerContact(id);
	}

	@GetMapping("/AllticketsPerEntreprise")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
	@ResponseBody
	public List<ticket> AllticketsPerEntreprise(@Param("id") Long id) {
		return TicketService.AllticketsPerEntreprise(id);
	}
}
