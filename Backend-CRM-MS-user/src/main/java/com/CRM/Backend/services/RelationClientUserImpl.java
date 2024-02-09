package com.CRM.Backend.services;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.servicesInterfaces.IRelationClientUserService;
import com.CRM.Backend.servicesInterfaces.IRoleEntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RelationClientUserImpl implements IRelationClientUserService {

    @Autowired
    com.CRM.Backend.repositories.RelationClientUserRepository RelationClientUserRepository;

    @Autowired
    com.CRM.Backend.repositories.EntrepriseRepository EntrepriseRepository;

    @Autowired
    com.CRM.Backend.repositories.ClientRepository ClientRepository;

    @Autowired
    com.CRM.Backend.repositories.UserRepository UserRepository;

    @Autowired
    IRoleEntrepriseService IRoleEntrepriseService;

    public List<RelationClientUser> retrieveAllRelationsClientUser() {
        return RelationClientUserRepository.findAll();
    }

    public RelationClientUser ajoutRelationClientUser(RelationClientUser e) {
        e.setDateCreation(new Date());
        return RelationClientUserRepository.save(e);
    }



    public void supprimerRelationClientUserPerID(Long id) {
        RelationClientUserRepository.delete(RelationClientUserRepository.findById(id).get());
    }

    public RelationClientUser RelationClientUserDetails(Long id) {
        return RelationClientUserRepository.findById(id).get();
    }

    public List<Client> MyCLientsUser(Long id) {
        List<RelationClientUser>  RelationClientUsers = RelationClientUserRepository.findAll();
        List<Client> MyCLients = new ArrayList<>();
        for (RelationClientUser RelationClientUser : RelationClientUsers) {
            if(RelationClientUser.getIdUser() != null && RelationClientUser.getIdUser().equals(id) && RelationClientUser.getTypeRelationClientUser().equals(TypeRelationClientUser.CLIENT)){
                MyCLients.add(ClientRepository.findById(RelationClientUser.getIdClient()).get());
            }
        }
        return MyCLients;
    }

    public List<Client> MyCLientsEntreprise(Long id) {
        List<RelationClientUser>  RelationClientUsers = RelationClientUserRepository.findAll();
        List<Client> MyCLients = new ArrayList<>();
        for (RelationClientUser RelationClientUser : RelationClientUsers) {
            if(RelationClientUser.getIdEntreprise() != null && RelationClientUser.getIdEntreprise().equals(id) && RelationClientUser.getTypeRelationClientUser().equals(TypeRelationClientUser.CLIENT) ){
                MyCLients.add(ClientRepository.findById(RelationClientUser.getIdClient()).get());
            }
        }
        return MyCLients;
    }

    public List<Contact> ContactsClient(Long id) {
        List<RelationClientUser>  RelationClientUsers = RelationClientUserRepository.findAll();
        List<Contact> Contacts = new ArrayList<>();
        for (RelationClientUser RelationClientUser : RelationClientUsers) {
            if(RelationClientUser.getIdClient() != null && RelationClientUser.getIdClient().equals(id) && RelationClientUser.getTypeRelationClientUser().equals(TypeRelationClientUser.CONTACT)){
                Contacts.add(UserRepository.findById(RelationClientUser.getIdUser()).get());
            }
        }
        return Contacts;
    }

    public List<RelationClientUserDTO> CLientsOfMyEntreprise(Long id) {
        List<RelationClientUser>  RelationClientUsers = RelationClientUserRepository.findAll();
        Entreprise e = IRoleEntrepriseService.retrieveEntreprisePercontatc(id);
        List<RelationClientUserDTO> MyCLients = new ArrayList<>();
        for (RelationClientUser RelationClientUser : RelationClientUsers) {
            if(RelationClientUser.getIdEntreprise() != null && RelationClientUser.getIdEntreprise().equals(e.getIdUser()) && RelationClientUser.getTypeRelationClientUser()== TypeRelationClientUser.CLIENT){
                RelationClientUserDTO dto = new RelationClientUserDTO();
                dto.setIdRelationClientUser(RelationClientUser.getIdRelationClientUser());
                if(RelationClientUser.getIdEntreprise() != null) {
                    dto.setEntreprise(EntrepriseRepository.findById(RelationClientUser.getIdEntreprise()).get());
                }
                else{
                    dto.setEntreprise(new Entreprise());
                }
                if(RelationClientUser.getIdClient() != null){
                    dto.setClient(ClientRepository.findById(RelationClientUser.getIdClient()).get());
                }
                else{
                    dto.setClient(new Client());
                }
                if (RelationClientUser.getIdUser() != null) {
                    dto.setUser(UserRepository.findById(RelationClientUser.getIdUser()).get());
                }
                else{
                    dto.setUser(new Contact());
                }
                MyCLients.add(dto);
            }
        }
        return MyCLients;
    }



    public Contact ResponsableAuClient(Long id) {
        List<RelationClientUser>  RelationClientUsers = RelationClientUserRepository.findAll();
        for (RelationClientUser RelationClientUser : RelationClientUsers) {
            if(RelationClientUser.getIdEntreprise() == id && RelationClientUser.getTypeRelationClientUser()== TypeRelationClientUser.CLIENT){
                return UserRepository.findById(RelationClientUser.getIdUser()).get();
            }
        }
        return null;
    }

    public RelationClientUser UpdateRelationClientUser(RelationClientUser e) {
        RelationClientUser R = RelationClientUserRepository.findById(e.getIdRelationClientUser()).get();
            R.setIdUser(e.getIdUser());
        return RelationClientUserRepository.save(R);
    }

    public void supprimerRelationClientUserPerIdUserAndIdClient(Long idClient , Long idUser) {
        List<RelationClientUser>  RelationClientUsers = RelationClientUserRepository.findAll();
        for(RelationClientUser RelationClientUser :RelationClientUsers){
            if(     RelationClientUser.getIdUser() != null &&
                    RelationClientUser.getIdClient() != null &&
                    RelationClientUser.getIdUser().equals(idUser) &&
                    RelationClientUser.getIdClient().equals(idClient)){
                RelationClientUserRepository.delete(RelationClientUser);
            }
        }
    }

    public List<Entreprise> ListEntreprisesPerContact(Long idContact) {
        List<RelationClientUser>  RelationClientUsers = RelationClientUserRepository.findAll();
        List<RelationClientUser>  RelationClient1 = new ArrayList<>();
        List<Entreprise>  listeEntreprises = new ArrayList<>();
        for(RelationClientUser RelationClientUser :RelationClientUsers){
            if(     RelationClientUser.getIdUser() != null &&
                    RelationClientUser.getIdUser().equals(idContact) &&
                    RelationClientUser.getTypeRelationClientUser().equals(TypeRelationClientUser.CONTACT)
            ){
                RelationClient1.add(RelationClientUser);
            }
        }
        for(RelationClientUser r1 :RelationClientUsers){
            for(RelationClientUser r2 :RelationClient1){
                if(     r1.getIdClient().equals(r2.getIdClient()) && r1.getTypeRelationClientUser().equals(TypeRelationClientUser.CLIENT)){
                    listeEntreprises.add(EntrepriseRepository.findById(r1.getIdEntreprise()).get());
                }
            }
        }
        return listeEntreprises;
    }

    public List<Client> ListClientsPerContact(Long idContact) {
        List<RelationClientUser>  RelationClientUsers = RelationClientUserRepository.findAll();
        List<Client>  clientList = new ArrayList<>();
        for(RelationClientUser RelationClientUser :RelationClientUsers){
            if(     RelationClientUser.getIdUser() != null &&
                    RelationClientUser.getIdUser().equals(idContact) &&
                    RelationClientUser.getTypeRelationClientUser().equals(TypeRelationClientUser.CONTACT)
            ){
                clientList.add(ClientRepository.findById(RelationClientUser.getIdClient()).get());
            }
        }
        return clientList;
    }

    public List<Entreprise> ListEntreprisePerClient(Long idClient) {
        List<RelationClientUser>  RelationClientUsers = RelationClientUserRepository.findAll();
        List<Entreprise>  entrepriseList = new ArrayList<>();
        for(RelationClientUser RelationClientUser :RelationClientUsers){
            if(     RelationClientUser.getIdClient() != null &&
                    RelationClientUser.getIdClient().equals(idClient) &&
                    RelationClientUser.getTypeRelationClientUser().equals(TypeRelationClientUser.CLIENT)
            ){
                entrepriseList.add(EntrepriseRepository.findById(RelationClientUser.getIdEntreprise()).get());
            }
        }
        return entrepriseList;
    }

    public List<Client> CLientsOfMyEntrepriseJustClients(Long id){
        List<RelationClientUser>  RelationClientUsers = RelationClientUserRepository.findAll();
        Entreprise e = IRoleEntrepriseService.retrieveEntreprisePercontatc(id);
        List<Client> MyCLients = new ArrayList<>();
        for (RelationClientUser RelationClientUser : RelationClientUsers) {
            if(RelationClientUser.getIdEntreprise() == e.getIdUser() && RelationClientUser.getTypeRelationClientUser()== TypeRelationClientUser.CLIENT){
                MyCLients.add(ClientRepository.findById(RelationClientUser.getIdClient()).get());
            }
        }
        return MyCLients;
    }
}
