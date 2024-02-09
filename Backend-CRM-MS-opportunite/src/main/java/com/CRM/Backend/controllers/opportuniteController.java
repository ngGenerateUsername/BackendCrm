package com.CRM.Backend.controllers;

import java.util.List;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.repositories.OpportuniteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import com.CRM.Backend.servicesInterfaces.IOpportuniteService;


@RestController
@RequestMapping("/ticket")
@CrossOrigin(origins = "*", maxAge = 3600)
public class opportuniteController {

	@Autowired
	IOpportuniteService OpportuniteService;


	@GetMapping("/AllOpportunites")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLEopportunite')")
	@ResponseBody
	public List<opportunite> Allopportunites() {
		return OpportuniteService.Allopportunites();
	}


	@GetMapping("/piste")
	public List<opportunite> getAllOpportunitesPISTE() {
		return OpportuniteService.AllopportunitesPISTE();
	}
	@GetMapping("/potentiel")
	public List<opportunite> getAllOpportunitesPOTENTIEL() {
		return OpportuniteService.AllopportunitesPOTENTIEL();
	}
	@GetMapping("/confirmee")
	public List<opportunite> getAllOpportunitesCONFIRMEE() {
		return OpportuniteService.AllopportunitesCONFIRMEE();
	}
	@GetMapping("/signee")
	public List<opportunite> getAllOpportunitesSIGNEE() {
		return OpportuniteService.AllopportunitesSIGNEE();
	}
	@GetMapping("/perdue")
	public List<opportunite> getAllOpportunitesPERDUE() {
		return OpportuniteService.AllopportunitesPERDUE();
	}
	@GetMapping("/DetailsOpportunite")
	//@PreAuthorize("hasAuthority('[ROLE_ADMIN]')")
	@ResponseBody
	public opportunite retrieveopportunite(@Param("id") Long id) {
		return OpportuniteService.retrieveopportunite(id);
	}

	@PostMapping("/addOpportunite")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLEOpportunite')")
	@ResponseBody
	public opportunite addOpportunite(@RequestBody opportunite opportunite) {
		return OpportuniteService.addopportunite(opportunite);
	}
	@PutMapping("/setStatusOpportunitePISTE/{idOpportunite}")

	@ResponseBody
	public opportunite statusOpportunitePISTE(@PathVariable("idOpportunite") Long id) {
		return OpportuniteService.statusOpportunitePISTE(id);
	}

	@PutMapping("/setStatusOpportuniteCONFIRMEE/{idOpportunite}")

	@ResponseBody
	public opportunite statusOpportuniteCONFIRMEE(@PathVariable("idOpportunite") Long id) {
		return OpportuniteService.statusOpportuniteCONFIRMEE(id);
	}

	@PutMapping("/setStatusOpportunitePERDUE/{opportuniteId}")

	@ResponseBody
	public opportunite statusOpportunitePERDUE(@PathVariable("opportuniteId") Long id) {
		return OpportuniteService.statusOpportunitePERDUE(id);
	}

	@PutMapping("/setStatusOpportuniteSIGNEE/{opportuniteId}")

	@ResponseBody
	public opportunite statusOpportuniteSIGNEE(@PathVariable("opportuniteId") Long id) {
		return OpportuniteService.statusOpportuniteSIGNEE(id);
	}
	@PutMapping("/setStatusOpportunitePOTENTIEL/{opportuniteId}")

	@ResponseBody
	public opportunite statusOpportunitePOTENTIEL(@PathVariable("opportuniteId") Long id) {
		return OpportuniteService.statusOpportunitePOTENTIEL(id);
	}
	@PutMapping("/updatePriorite/{opportuniteId}")

	@ResponseBody
	public opportunite updatePriorite(@PathVariable("opportuniteId") Long id,@RequestBody prioriteOpportunite prioriteOpp) {
		return OpportuniteService.updatePriorite(id,prioriteOpp);
	}
	@GetMapping("/actifOpportunities")

	@ResponseBody
	public List<opportunite> actifOpportunities() {
		return OpportuniteService.actifOpportunities();
	}
	@GetMapping("/inactifOpportunities")

	@ResponseBody
	public List<opportunite> inactifOpportunities() {
		return OpportuniteService.inactifOpportunities();
	}


	@PutMapping("/deleteOpportunite/{opportuniteID}")

	@ResponseBody
	public opportunite deleteOpportunite(@PathVariable("opportuniteID") Long id) {
		return OpportuniteService.deleteOpportunite(id);
	}
}
