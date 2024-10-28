package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Reclamation;

import java.util.List;

public interface IReclamationService {
    public Reclamation createrec (Reclamation R , Long idcmd) ;
    public List<Reclamation> getReclamationByidcmd(Long idcmd);
    public List<Reclamation> getReclamationByidclt(Long idclt);
    //public List<Reclamation> getReclamationByidetse(Long etse);

    List<Reclamation> getReclamationByidetse(Long etse);

    public Reclamation getReclamationById(Long id);

    Reclamation updatereclmation(Reclamation R, Long idr,Long idcom );
}
