package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Contact;
import com.CRM.Backend.entities.Entreprise;
import com.CRM.Backend.entities.RoleEntreprise;

import java.util.List;


public interface IRoleEntrepriseService {
    public List<RoleEntreprise> retrieveAllroleEntreprises();
    public RoleEntreprise ajoutRoleEntreprise(RoleEntreprise r);
    public RoleEntreprise roleEntrepriseDetails(Long id);


    public List<RoleEntreprise> retrieveAllroleEntreprisesPerEntreprise(Long id) ;
    public List<RoleEntreprise> retrieveAllroleEntreprisesPerProp(Long id) ;
    public List<RoleEntreprise> retrieveAllroleEntreprisesPerContact(Long id) ;



    public Contact retrievePropPerEntreprise(Long id);
    public Entreprise retrieveEntreprisePercontatc(Long id);
    public List<Contact> retrievecontactsPerEntreprise(Long id);
    public Entreprise retrieveEntreprisesPerProp(Long id);
    public List<Contact> retrieveCommercialPerEntreprise(Long id);
    public List<Contact> retrieveRespTicketPerEntreprise(Long id);
}