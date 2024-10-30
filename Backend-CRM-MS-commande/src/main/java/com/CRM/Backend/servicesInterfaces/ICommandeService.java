package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Commande;
import com.CRM.Backend.entities.Dto.DTOCommande;
import com.CRM.Backend.entities.Dto.DTOLigneCommande;
import com.CRM.Backend.entities.LigneCommande;
import com.CRM.Backend.entities.Notif;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public interface ICommandeService {
    public String addCommande(DTOCommande  dtoCommande, Long idcontact ,Long idetse);
    public List<Commande> getAllCommande();
    public Commande getById(Long idC);
    public void removeCommande(Long Commande);
    public Commande updateCommande(Commande commande);
    public List<Commande> getAllCommandebyuser(Long idClient );
    public List<DTOLigneCommande>  getcommanddetails(Long idcmd);
    public List<Commande> getAllCommandebyidentreprise(Long identreprise);
public boolean validate (Long idcmd);
    //public ResponseEntity<List<String>> available( );

    boolean validatef(Long idcmd);

    public void scheduledCheck();

    // Method triggered by REST API to accept dynamic idets from the frontend
    public ResponseEntity<List<String>> available( );
    public  List<Notif> allnotifetse();
    public Notif getnotifbyid(Long id );
}
