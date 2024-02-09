package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.*;

import java.util.List;

public interface IRelationClientUserService {

    public List<RelationClientUser> retrieveAllRelationsClientUser();
    public RelationClientUser ajoutRelationClientUser(RelationClientUser e);
    public void supprimerRelationClientUserPerID(Long id);
    public void supprimerRelationClientUserPerIdUserAndIdClient(Long idClient , Long idUser);
    public RelationClientUser RelationClientUserDetails(Long id);
    public List<Client> MyCLientsUser(Long id);
    public List<Client> MyCLientsEntreprise(Long id);
    public List<Contact> ContactsClient(Long id);
    public List<RelationClientUserDTO> CLientsOfMyEntreprise(Long id);
    public List<Client> CLientsOfMyEntrepriseJustClients(Long id);
    public RelationClientUser UpdateRelationClientUser(RelationClientUser e);
    public List<Entreprise> ListEntreprisesPerContact(Long idContact);
    public List<Client> ListClientsPerContact(Long idContact);
    public List<Entreprise> ListEntreprisePerClient(Long idClient);
}
