package com.CRM.Backend.servicesInterfaces;

import java.util.List;

import com.CRM.Backend.DTO.AddOffreRequest;
import com.CRM.Backend.entities.Offre;

public interface IOffreService {
    public Offre addNewOffreOrEdit(AddOffreRequest newOffre);

    public List<Offre> listOffre();
    public List<Offre> getOffreOfCommercial(Long id);

    //get detail of offre
    public Offre getOffreWithProducts(Long idOffre);

    //delete offre
    public void deleteOffreByid(Long idOffre);




}
