package com.CRM.Backend.services;

import com.CRM.Backend.entities.Client;
import com.CRM.Backend.entities.StatusUser;
import com.CRM.Backend.entities.dto.DtoClient;
import com.CRM.Backend.repositories.ClientRepository;
import com.CRM.Backend.servicesInterfaces.IClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ClientServiceImpl implements IClientService {
   
	@Autowired
	ClientRepository ClientRepository;

    public List<Client> retrieveAllClients() {
		return ClientRepository.findAll();
	}

    public Client ajoutClient(DtoClient e) {
    	Client client=new Client();
    	client.setAdresse(e.getAdresse());
		client.setCA(e.getCA());
		client.setDescription(e.getDescription());
		client.setNomEntreprise(e.getNomEntreprise());
		client.setDomaine(e.getDomaine());
		client.setMail(e.getMail());
		client.setNumFiscal(e.getNumFiscal());
		client.setNumTel(e.getNumTel());

		client.setStatusUser(StatusUser.ACTIF);
		client.setImage("company.png");
		client.setDateCreation(e.getDateCreation());
		return ClientRepository.save(client);
	}	

	public Client updateClient(DtoClient cc) {
		try {
			Client ccc = ClientRepository.findById(cc.getIdClient()).get();

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
			if(cc.getNomEntreprise() != null){
				ccc.setNomEntreprise(cc.getNomEntreprise());
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
			return ClientRepository.save(ccc);
		}
		catch(Exception e) {
			return null;
		}
	}

    public Client ClientDetails(Long id) {
		return ClientRepository.findById(id).get();
	}






}
