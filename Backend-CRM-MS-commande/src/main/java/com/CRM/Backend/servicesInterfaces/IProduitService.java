package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Dto.DTOProduit;
import com.CRM.Backend.entities.Dto.DTOProduitCmd;
import com.CRM.Backend.entities.Notif;
import com.CRM.Backend.entities.Produit;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProduitService {

    public Produit addProduit(DTOProduit dtoProduit,Long IDcategorie );
    public List<Produit> getAllProduit(Long IdEntreprise );
    public Produit getById(Long idProduit);
    public void removeProduit(Long idProduit);
    public List<DTOProduitCmd> getAllProduitCMD();

    public Produit updateProduit(DTOProduit dtoProduit) ;
    public String getnometse( Long idprod ) ;


    public List<DTOProduitCmd> getAllProduitCMDBYCategorie( Long id);
    //public ResponseEntity<List<String>> available(Long idets);
    public Notif findByIdProduit(Long idproduit);

}
