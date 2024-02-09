package com.CRM.Backend.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.entities.Dto.DTOMsgAdminCreate;
import com.CRM.Backend.entities.Dto.DTOMsgAdminStatus;
import com.CRM.Backend.repositories.MsgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRM.Backend.servicesInterfaces.IMsgService;

@Service
public class MsgServiceImpl implements IMsgService{
    @Autowired
	MsgRepository msgRepository;

	public MsgAdmin addMsg(DTOMsgAdminCreate DTOMsgAdminCreate) {
		try {
             com.CRM.Backend.entities.MsgAdmin msgAdmin = new MsgAdmin();
			msgAdmin.setTitre(DTOMsgAdminCreate.getTitre());
			msgAdmin.setDescription(DTOMsgAdminCreate.getDescription());
			msgAdmin.setMail(DTOMsgAdminCreate.getMail());
			msgAdmin.setDateCreation(new Date());
			msgAdmin.setStatusMsg(statusMsg.ATTENTE);

			MsgAdmin d=msgRepository.save(msgAdmin);
			return d;
		}
		catch(Exception e) {
			System.out.println("erreur domain add : "+e.getMessage());
			return null;
		}
	}

	public void deleteMsg(Long id) {
		try {
			msgRepository.deleteById(id);
		}
		catch(Exception e) {
			System.out.println("erreur domain delete : "+id);
		}
	}


	public MsgAdmin retrieveMsg(Long id) {
		try {
			MsgAdmin d=msgRepository.findById(id).orElse(null);
			return d;
		}
		catch(Exception e) {
			//log.info("erreur domain retrieve : "+e.getMessage());
			return null;
		}
	}

	public MsgAdmin SetstatusMsg(DTOMsgAdminStatus DTOMsgAdminStatus) {
		MsgAdmin MsgAdmin=retrieveMsg(DTOMsgAdminStatus.getIdMsg());

		if(DTOMsgAdminStatus.getStatusMsg().equals(statusMsg.ATTENTE))
		{
			MsgAdmin.setStatusMsg(statusMsg.ATTENTE);
		}
		if(DTOMsgAdminStatus.getStatusMsg().equals(statusMsg.TRAITE))
		{
			MsgAdmin.setStatusMsg(statusMsg.TRAITE);
			MsgAdmin.setDateTraitement(new Date());
		}
		return msgRepository.save(MsgAdmin);
		}

	public List<MsgAdmin> AllMsgs() {
		List<MsgAdmin> Msgs=msgRepository.findAll();
		return Msgs;
	}

}
