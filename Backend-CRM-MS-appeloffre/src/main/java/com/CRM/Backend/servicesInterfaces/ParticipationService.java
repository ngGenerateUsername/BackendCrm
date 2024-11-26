package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Appeloffre;
import com.CRM.Backend.entities.Participation;

import java.util.List;

public interface ParticipationService {
    public String participate (Participation appeloffre , Long idf, Long idao);


public void setBestFournisseurForTenders(List<Appeloffre> appelsOffres);


}
