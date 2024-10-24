package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Dto.DTOFacture;
import com.CRM.Backend.entities.Dto.DTOProduit;
import com.CRM.Backend.entities.Facture;
import com.CRM.Backend.entities.LigneFacture;
import com.CRM.Backend.entities.Produit;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IFactureService {
    public Facture addFacture(DTOFacture dtoFacture);
    public List<Facture> getAllFacturebyidetse(Long id);

    List<Facture> getAllFacture();

    public Facture getById(Long idacture );
    public void removeFacture(Long idacture );
    public Facture updateFacture(DTOFacture dtoFacture);

    public ResponseEntity cratelf(Long idcmd);
    public List<LigneFacture> getAllLigneFacture(Long idcmd);
}
