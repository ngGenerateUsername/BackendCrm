package com.CRM.Backend.services;

import com.CRM.Backend.entities.Devis;
import com.CRM.Backend.entities.LigneDevis;
import com.CRM.Backend.repositories.DevisRepository;
import com.CRM.Backend.repositories.LigneRepository;
import com.CRM.Backend.servicesInterfaces.IDevisService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class IDevisServiceImp implements IDevisService {

    @Autowired
    private DevisRepository _devisOrm;
    
    @Autowired
    private LigneRepository _ligneDevisOrm;
    
    
    @Override
    public List<Devis> getAll() {
        return _devisOrm.findAll();
    }



    @Override
    @Transactional
    public Devis addDevis(Devis devis) {
        // TODO Auto-generated method stub

        if(devis.getId() != null)
        {
            // factureMain.setId(facture.getId());    
            _ligneDevisOrm.deleteByDevisId(devis.getId());
        }
          

        for(LigneDevis ligne:devis.getLignes())
        {
            ligne.setDevis(devis);
        }
        return _devisOrm.save(devis);
    }




    //verify the existance of a facture then delete it
    @Override
    public boolean deleteDevis(Long id) {
        // TODO Auto-generated method stub
        
        if(_devisOrm.existsById(id))
        {
            _devisOrm.deleteById(id);
            return true;
        }
        return false;
    }



    @Override
    public List<Devis> findClientDevis(Long idClient) {
        // TODO Auto-generated method stub
        return _devisOrm.findByIdClient(idClient);
                
    }



    @Override
    public List<Devis> findByIdCommerciale(Long idCommerciale) {
        
        return _devisOrm.findByIdCommercial(idCommerciale);
    }



    @Override
    public Devis findById(Long idFacture) {
        // TODO Auto-generated method stub
        if(_devisOrm.existsById(idFacture))
        return _devisOrm.findById(idFacture).get();
        return null;
    };

}
