package com.CRM.Backend.controllers;

import java.util.List;

import com.CRM.Backend.entities.Dto.DTOMsgAdminCreate;
import com.CRM.Backend.entities.Dto.DTOMsgAdminStatus;
import com.CRM.Backend.entities.MsgAdmin;
import com.CRM.Backend.repositories.MsgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import com.CRM.Backend.servicesInterfaces.IMsgService;


@RestController
@RequestMapping("/ticket")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MsgController {

	@Autowired
	private MsgRepository msgRepository;

	@Autowired
	IMsgService msgService;


	@GetMapping("/AllMsgs")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
	@ResponseBody
	public List<MsgAdmin> AllMsgs() {
		return msgService.AllMsgs();
	}

	@GetMapping("/DetailsMsg")
	//@PreAuthorize("hasAuthority('[ROLE_ADMIN]')")
	@ResponseBody
	public MsgAdmin DetailsMsg(@Param("id") Long id) {
		return msgService.retrieveMsg(id);
	}

	@PostMapping("/addMsg")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
	@ResponseBody
	public MsgAdmin addTicket(@RequestBody DTOMsgAdminCreate DTOMsgAdminCreate) {
		return msgService.addMsg(DTOMsgAdminCreate);
	}

	@DeleteMapping("/removeTicket/{id}")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
	@ResponseBody
	public void deleteMsg(@PathVariable("id") Long id) {
		msgService.deleteMsg(id);
	}


	@PutMapping("/setStatusMsg")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
	@ResponseBody
	public MsgAdmin setStatusMsg(@RequestBody DTOMsgAdminStatus DTOMsgAdminStatus) {

		return msgService.SetstatusMsg(DTOMsgAdminStatus);
	}
}








