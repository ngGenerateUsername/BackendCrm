package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Categorie;
import com.CRM.Backend.entities.Dto.DTOCategorie;

import java.util.List;

public interface ICategorieService {
    public Categorie addCategorie(DTOCategorie categorie );
    public List<Categorie> getAllCategorie();
    public Categorie getById(Long idCategorie);
    public void removeCategorie(Long idCategorie);
    public Categorie updateCategorie(Categorie categorie);

}
