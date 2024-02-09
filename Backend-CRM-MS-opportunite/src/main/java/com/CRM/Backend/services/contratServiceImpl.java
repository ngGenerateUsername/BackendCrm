package com.CRM.Backend.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.repositories.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRM.Backend.servicesInterfaces.IContratService;

@Service
public class contratServiceImpl implements IContratService {
	@Autowired
	ContratRepository contratRepository;

    @Override
	public contrat addcontrat(contrat contrat) {
		try {
			return contratRepository.save(contrat);
		}
		catch(Exception e) {
			//log.info("erreur domain add : "+e.getMessage());
			return null;
		}
	}



	public contrat retrievecontrat(Long id) {
		try {
			contrat d = contratRepository.findById(id).orElse(null);
			return d;
		} catch (Exception e) {
			// log.info("erreur domain retrieve : "+e.getMessage());
			return null;
		}
	}

	public List<contrat> Allcontrats() {
		List<contrat> domains = contratRepository.findAll();
		return domains;
	}

	
}
