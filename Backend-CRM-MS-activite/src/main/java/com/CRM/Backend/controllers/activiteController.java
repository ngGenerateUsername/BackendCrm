package com.CRM.Backend.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import com.CRM.Backend.entities.activite;
import com.CRM.Backend.servicesInterfaces.IActiviteService;


@RestController
@RequestMapping("/activite")
@CrossOrigin(origins = "*", maxAge = 3600)
public class activiteController {
    @Autowired
	IActiviteService ActiviteService;

	@GetMapping("/AllActivites")

	@ResponseBody
	public List<activite> Allactivites() {
		return ActiviteService.Allactivites();
	}

	@GetMapping("/inactifActivites")

	@ResponseBody
	public List<activite> inactifActivites() {
		return ActiviteService.inactifActivites();
	}

	@GetMapping("/actifActivites")

	@ResponseBody
	public List<activite> actifActivites() {
		return ActiviteService.actifActivites();
	}

	@GetMapping("/DetailsActivite")
	//@PreAuthorize("hasAuthority('[ROLE_ADMIN]')")
	@ResponseBody
	public activite retrieveactivite(@Param("id") Long id) {
		return ActiviteService.retrieveactivite(id);
	}
	@PostMapping("/addActivite")
	
	@ResponseBody
	public activite addactivite(@RequestBody activite activite){
		return ActiviteService.addactivite(activite);
	}


	@PutMapping("/setActiviteInactif/{activiteId}")
	
	@ResponseBody
	public activite setActiviteInactif(@PathVariable("activiteId") Long id) {
		return ActiviteService.setActiviteInactif(id);
	}


	@PutMapping("/modifyActivite")
	
	@ResponseBody
	public activite updateActivite(@RequestBody activite activite){
		return ActiviteService.updateactivite(activite);
	}

	@PutMapping("/setStatusActiviteENCOURS/{activiteId}")
	
	@ResponseBody
	public activite statusActiviteENCOURS(@PathVariable("activiteId") Long id) {
		return ActiviteService.statusActiviteENCOURS(id);
	}

	@PutMapping("/setStatusActiviteTERMINE/{activiteId}")
	
	@ResponseBody
	public activite statusActiviteTERMINE(@PathVariable("activiteId") Long id) {
		return ActiviteService.statusActiviteTERMINE(id);
	}
	@PutMapping("/setRelationActiviteOpportunite/{activiteId}")
	
	@ResponseBody
	public activite relationActiviteOpportunite(@PathVariable("activiteId") Long id) {
		return ActiviteService.relationActiviteOpportunite(id);
	}
	@PutMapping("/setRelationActiviteTicket/{activiteId}")
	
	@ResponseBody
	public activite relationActiviteTicket(@PathVariable("activiteId") Long id) {
		return ActiviteService.relationActiviteTicket(id);
	}



	@PutMapping("/AddNotesActivite/{activiteId}")
	
	@ResponseBody
	public activite notesActivite(@PathVariable("activiteId") Long id,@RequestBody String notes) {
		return ActiviteService.notesActivite(id,notes);
	}


	@PutMapping("/affecteCommercial/{activiteId}/{idComm}")
	
	@ResponseBody
	public activite affecteCommercial(@PathVariable("activiteId") Long id,@PathVariable("idComm") Long idComm) {
		return ActiviteService.affecteCommercial(id,idComm);
	}

	@GetMapping("/AllactivitesStatus/{status}")
	
	@ResponseBody
	public List<activite> AllactivitesStatus(@PathVariable("status") String status) {
		return ActiviteService.AllactivitesStatus(status);
	}

	@GetMapping("/AllactivitesType/{type}")
	
	@ResponseBody
	public List<activite> AllactivitesType(@PathVariable("type") String type) {
		return ActiviteService.AllactivitesType(type);
	}
	@GetMapping("/activitesForOpportunite/{idOpportunite}")
	@ResponseBody
	public List<activite> getActivitesForOpportunite(@PathVariable("idOpportunite") Long idOpportunite) {
		return ActiviteService.getActivitesForOpportunite(idOpportunite);
	}
	@GetMapping("/activitesForTicket/{idTicket}")
	@ResponseBody
	public List<activite> getActivitesForTicket(@PathVariable("idTicket") Long idTicket) {
		return ActiviteService.getActivitesForTicket(idTicket);
	}
}
