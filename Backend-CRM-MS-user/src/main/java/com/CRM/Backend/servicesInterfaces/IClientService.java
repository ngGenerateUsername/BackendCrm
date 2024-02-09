package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Client;
import com.CRM.Backend.entities.Entreprise;
import com.CRM.Backend.entities.dto.DtoClient;
import com.CRM.Backend.entities.dto.DtoEntreprise;

import java.util.List;


public interface IClientService {
    public List<Client> retrieveAllClients();
    public Client ajoutClient(DtoClient e);
    public Client updateClient(DtoClient cc);
    public Client ClientDetails(Long id);
}