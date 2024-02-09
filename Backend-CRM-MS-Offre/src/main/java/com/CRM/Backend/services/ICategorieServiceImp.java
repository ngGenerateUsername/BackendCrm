package com.CRM.Backend.services;

import com.CRM.Backend.entities.Categorie;
import com.CRM.Backend.repositories.CategorieRepository;
import com.CRM.Backend.servicesInterfaces.ICategorieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class ICategorieServiceImp implements ICategorieService {

    CategorieRepository categorieRepository;

    @Override
    public Categorie addCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public List<Categorie> getAllCategorie() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie getById(Long idCategorie) {
        return categorieRepository.findById(idCategorie).orElse(null);
    }

    @Override
    public void removeCategorie(Long idCategorie) {
        categorieRepository.deleteById(idCategorie);

    }

    @Override
    public Categorie updateCategorie(Categorie categorie) {
        return  categorieRepository.save(categorie);

    }
}
