package com.CRM.Backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.repositories.*;
import com.CRM.Backend.servicesInterfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleEntrepriseImpl implements IRoleEntrepriseService {
    
    @Autowired
	RoleEntrepriseRepository roleEntrepriseRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	EntrepriseRepository entrepriseRepository;

    public List<RoleEntreprise> retrieveAllroleEntreprises() {
		List<RoleEntreprise> role_entreprises= roleEntrepriseRepository.findAll();

		return role_entreprises;
	}

    public RoleEntreprise ajoutRoleEntreprise(RoleEntreprise r) {
		return roleEntrepriseRepository.save(r);
	}
    
    public RoleEntreprise roleEntrepriseDetails(Long id) {
		return roleEntrepriseRepository.findById(id).get();
	}

    public List<RoleEntreprise> retrieveAllroleEntreprisesPerEntreprise(Long id) {
		
		return roleEntrepriseRepository.findByIdEntreprise(id);
	}

	public List<RoleEntreprise> retrieveAllroleEntreprisesPerProp(Long id) {
		List<RoleEntreprise> role_entreprises= roleEntrepriseRepository.findByIdContact(id);
		return  role_entreprises.stream().filter(companie->  companie.getRoleUser().equals(RoleUser.ROLE_PROPRIETAIRE))
				.distinct()
				.collect(Collectors.toList());
	}

	public List<RoleEntreprise> retrieveAllroleEntreprisesPerContact(Long id) {

		return roleEntrepriseRepository.findByIdContact(id);
	}

	public Contact retrievePropPerEntreprise(Long id) {
	List<RoleEntreprise>  role_entreprises = roleEntrepriseRepository.findByIdEntreprise(id);
		for (RoleEntreprise roleEntreprise : role_entreprises) {
		if(roleEntreprise.getRoleUser() == RoleUser.ROLE_PROPRIETAIRE){
			return userRepository.findById(roleEntreprise.getIdContact()).get();
		}
		}
		return new Contact();
		/*try {
			RoleEntreprise company = roleEntrepriseRepository.findByIdEntrepriseAndRoleContact(id, RoleContact.ROLE_PROPRIETAIRE.toString());
			return contactRepository.findById(company.getIdContact()).get();
		} catch (Exception e ){
			System.out.println( "exception occured is ");
			System.out.println(e);
			return null;

		 */
		}

	public Entreprise retrieveEntreprisePercontatc(Long id) {
		List<RoleEntreprise>  role_entreprises = roleEntrepriseRepository.findAll();
		for (RoleEntreprise roleEntreprise : role_entreprises) {
		if(roleEntreprise.getIdContact().equals(id))
			return entrepriseRepository.findById(roleEntreprise.getIdEntreprise()).get();
		}
		return new Entreprise();
	}

	public List<Contact> retrievecontactsPerEntreprise(Long id) {
		List<RoleEntreprise>  role_entreprises = roleEntrepriseRepository.findByIdEntreprise(id);
		 List<Contact> Contacts = new ArrayList<>();
		for (RoleEntreprise roleEntreprise : role_entreprises) {
		if(roleEntreprise.getRoleUser() != RoleUser.ROLE_PROPRIETAIRE){
			Contacts.add(userRepository.findById(roleEntreprise.getIdContact()).get());
		}
		}
		return Contacts;
	}

	public Entreprise retrieveEntreprisesPerProp(Long id) {
		List<RoleEntreprise>  role_entreprises = roleEntrepriseRepository.findAll();
		//List<entreprise> listeEntreprise = new ArrayList<>();
		for (RoleEntreprise roleEntreprise : role_entreprises) {
		if(roleEntreprise.getIdContact() == id && roleEntreprise.getRoleUser()== RoleUser.ROLE_PROPRIETAIRE){
			return entrepriseRepository.findById(roleEntreprise.getIdEntreprise()).get();
		}
		}
		return new Entreprise();
	}

	private List<RoleEntreprise> filterListByRole(Long id , String role){
		List<RoleEntreprise> role_entreprises= roleEntrepriseRepository.findByIdContact(id);
		return  role_entreprises.stream().filter(companie->  companie.getRoleUser().toString().equals(role))
				.distinct()
				.collect(Collectors.toList());
	}

	private RoleEntreprise filterByRole(Long id , String role){
		List<RoleEntreprise> role_entreprises= roleEntrepriseRepository.findByIdContact(id);
		return role_entreprises.stream().filter(companie->  companie.getRoleUser().toString().equals(role)).findFirst().get();
	}

	public List<Contact> retrieveCommercialPerEntreprise(Long id) {
		List<RoleEntreprise>  role_entreprises = roleEntrepriseRepository.findByIdEntreprise(id);
		List<Contact> Contacts = new ArrayList<>();
		for (RoleEntreprise roleEntreprise : role_entreprises) {
			if(roleEntreprise.getRoleUser() == RoleUser.ROLE_COMMERCIAL){
				Contacts.add(userRepository.findById(roleEntreprise.getIdContact()).get());
			}
		}
		return Contacts;
	}

	public List<Contact> retrieveRespTicketPerEntreprise(Long id) {
		List<RoleEntreprise>  role_entreprises = roleEntrepriseRepository.findByIdEntreprise(id);
		List<Contact> Contacts = new ArrayList<>();
		for (RoleEntreprise roleEntreprise : role_entreprises) {
			if(roleEntreprise.getRoleUser() == RoleUser.ROLE_RESPONSABLETICKET){
				Contacts.add(userRepository.findById(roleEntreprise.getIdContact()).get());
			}
		}
		return Contacts;
	}

}
