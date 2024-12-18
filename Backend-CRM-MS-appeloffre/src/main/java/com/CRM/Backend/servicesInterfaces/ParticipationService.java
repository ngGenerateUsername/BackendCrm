package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Appeloffre;
import com.CRM.Backend.entities.Participation;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ParticipationService {
    public String participate (Participation appeloffre , MultipartFile pdfFile , Long idf, Long idao) throws IOException;


public void setBestFournisseurForTenders(List<Appeloffre> appelsOffres);


}
