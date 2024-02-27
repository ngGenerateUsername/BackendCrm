package com.CRM.Backend.services;

import com.CRM.Backend.repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRM.Backend.servicesInterfaces.ICommandeService;

@Service
public class ICommandeServiceImpl implements ICommandeService {
    @Autowired
    CommandeRepository commandeRepository;

}
