package com.CRM.Backend.controllers;

import java.util.List;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.repositories.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import com.CRM.Backend.servicesInterfaces.IContratService;


@RestController
@RequestMapping("/ticket")
@CrossOrigin(origins = "*", maxAge = 3600)
public class contratController {

	@Autowired
	IContratService ContratService;


	@GetMapping("/AllContrats")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLEcontrat')")
	@ResponseBody
	public List<contrat> Allcontrats() {
		return ContratService.Allcontrats();
	}

	@GetMapping("/DetailsContrat")
	//@PreAuthorize("hasAuthority('[ROLE_ADMIN]')")
	@ResponseBody
	public contrat retrievecontrat(@Param("id") Long id) {
		return ContratService.retrievecontrat(id);
	}

	@PostMapping("/addContrat")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLEcontrat')")
	@ResponseBody
	public contrat addContrat(@RequestBody contrat contrat) {
		return ContratService.addcontrat(contrat);
	}
}
