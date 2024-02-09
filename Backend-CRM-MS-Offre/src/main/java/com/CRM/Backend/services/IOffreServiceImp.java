package com.CRM.Backend.services;

import com.CRM.Backend.DTO.AddOffreChild;
import com.CRM.Backend.DTO.AddOffreRequest;
import com.CRM.Backend.entities.LigneOffre;
import com.CRM.Backend.entities.Offre;
import com.CRM.Backend.entities.Produit;
import com.CRM.Backend.entities.TypeOffre;
import com.CRM.Backend.repositories.LigneOffreRepository;
import com.CRM.Backend.repositories.OffreRepository;
import com.CRM.Backend.repositories.ProduitRepository;
import com.CRM.Backend.servicesInterfaces.IOffreService;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class IOffreServiceImp implements IOffreService {

    OffreRepository offreRepository;
    ProduitRepository prodRepo;

    LigneOffreRepository ligneRepo;


    @Override
    @Transactional
    public Offre addNewOffreOrEdit(AddOffreRequest newOffre) {

              //prepare offre
              Offre offre=Offre.builder()
              .title(newOffre.getTitle())
              .totaleHT(newOffre.getTotalePrixHT())
              .totaleHTTC(newOffre.getTotalePrixHTTC())
              .tvaMontant(newOffre.getTvaMontant())
              .idCommerciale(newOffre.getIdCommerciale())
              .typeOffre(TypeOffre.valueOf(newOffre.getTypeOffre()))
              .build();


              //if this function going to work as an edit feature
              if(newOffre.getIdOffre() != null) 
                {
                    offre.setIdOffre(newOffre.getIdOffre());
                   ligneRepo.deleteByOffreIdOffre(newOffre.getIdOffre());
                }
            
          //find product by id and replace it as an object inside the entity;
          List<LigneOffre> ligneToAdd = new ArrayList<LigneOffre>();
          for (AddOffreChild offreChild : newOffre.getListOffre()) {
              Produit produit = prodRepo.findById(offreChild.getIdProduit()).get();
  
  
              LigneOffre lOffre = LigneOffre
              .builder()
              .offre(offre)
              .PrixTTC(offreChild.getPrixHTTC())
              .prixHT(offreChild.getPrixHT())
              .remise(offreChild.getRemise())
              .produit(produit)
              .qte(offreChild.getQte())
              .build();    
              ligneToAdd.add(lOffre);
              
          }
          offre.setLigneOffres(ligneToAdd);
          return offreRepository.save(
              offre
          );

    }


    //offre calculator
    @Override
    public List<Offre> listOffre() {
        // TODO Auto-generated method stub
        return offreRepository.findByTypeOffre(TypeOffre.Offre);
    }

    @Override
    public List<Offre> getOffreOfCommercial(Long id) {
        // TODO Auto-generated method stub
        return offreRepository.findByIdCommerciale(id);
    }

    @Override
    public Offre getOffreWithProducts(Long idOffre) {
        // TODO Auto-generated method stub
        return offreRepository.findById(idOffre).orElse(null);
        
    }

    @Override
    public void deleteOffreByid(Long idOffre) {
        // TODO Auto-generated method stub
        offreRepository.deleteById(idOffre);
    }


}
