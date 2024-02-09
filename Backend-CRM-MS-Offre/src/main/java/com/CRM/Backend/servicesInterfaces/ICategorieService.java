package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Categorie;

import java.util.List;

public interface ICategorieService {
    public Categorie addCategorie(Categorie categorie);
    public List<Categorie> getAllCategorie();
    public Categorie getById(Long idCategorie);
    public void removeCategorie(Long idCategorie);
    public Categorie updateCategorie(Categorie categorie);

}
