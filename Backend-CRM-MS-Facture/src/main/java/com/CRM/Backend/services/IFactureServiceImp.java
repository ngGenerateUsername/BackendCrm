package com.CRM.Backend.services;

import com.CRM.Backend.entities.Facture;
import com.CRM.Backend.entities.LigneFacture;
import com.CRM.Backend.repositories.FactureRepository;
import com.CRM.Backend.repositories.LigneRepository;
import com.CRM.Backend.servicesInterfaces.IFactureService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;



@AllArgsConstructor
@Service
public class IFactureServiceImp implements IFactureService {
    
    @Autowired
    private FactureRepository _factOrm;
    
    @Autowired
    private LigneRepository _ligneFactureOrm;
    
    
    @Override
    public List<Facture> getAll() {
        return _factOrm.findAll();
    }



    @Override
    @Transactional
    public Facture addFacture(Facture facture) {
        // TODO Auto-generated method stub

        if(facture.getId() != null)
        {
            // factureMain.setId(facture.getId());    
            _ligneFactureOrm.deleteByFactureId(facture.getId());
        }
          

        for(LigneFacture ligne:facture.getLignes())
        {
            ligne.setFacture(facture);
        }
        return _factOrm.save(facture);
    }




    //verify the existance of a facture then delete it
    @Override
    public boolean deleteFacture(Long id) {
        // TODO Auto-generated method stub
        
        if(_factOrm.existsById(id))
        {
            _factOrm.deleteById(id);
            return true;
        }
        return false;
    }



    @Override
    public List<Facture> findClientFacture(Long idClient) {
        // TODO Auto-generated method stub
        return _factOrm.findByIdClient(idClient);
                
    }



    @Override
    public List<Facture> findByIdCommerciale(Long idCommerciale) {
        
        return _factOrm.findByIdCommercial(idCommerciale);
    }



    @Override
    public Facture findById(Long idFacture) {
        // TODO Auto-generated method stub
        if(_factOrm.existsById(idFacture))
        return _factOrm.findById(idFacture).get();
        return null;
    }



}
