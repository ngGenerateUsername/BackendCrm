package com.CRM.Backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.*;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.repositories.RoleEntrepriseRepository;
import com.CRM.Backend.services.RoleEntrepriseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class RoleEntrepriseServiceImplTest {

    @Mock
    private RoleEntrepriseRepository RoleEntrepriseRepository;

    @InjectMocks
    private RoleEntrepriseImpl RoleEntrepriseImpl;

    private Entreprise entreprise1;
    private Contact contact1,contact2,contact3;
    private RoleEntreprise roleEntreprise1,roleEntreprise2,roleEntreprise3;

    @BeforeEach
    public void setup() {
        entreprise1 = Entreprise.builder().idUser(31L).nomEntreprise("entreprise1").build();
        contact1 = Contact.builder().idUser(41L).nom("contact1").build();
        contact2 = Contact.builder().idUser(42L).nom("contact2").build();
        contact3 = Contact.builder().idUser(43L).nom("contact3").build();
        roleEntreprise1 = RoleEntreprise.builder().idRole(1L).idEntreprise(31L).idContact(41L).roleUser(RoleUser.ROLE_PROPRIETAIRE).build();
        roleEntreprise2 = RoleEntreprise.builder().idRole(2L).idEntreprise(31L).idContact(42L).roleUser(RoleUser.ROLE_COMMERCIAL).build();


    }

    @DisplayName("JUnit test")
    @Test
    void Test() {
        System.out.println(entreprise1);
        System.out.println(contact1);
        System.out.println(roleEntreprise1);
    }

    @Test
    void retrieveAllFournisseursTest() {
        roleEntreprise3 = RoleEntreprise.builder().idRole(3L).idEntreprise(31L).idContact(43L).roleUser(RoleUser.ROLE_RESPONSABLETICKET).build();
        given(RoleEntrepriseRepository.findAll()).willReturn(new ArrayList<>(Arrays.asList(roleEntreprise1, roleEntreprise2,roleEntreprise3)));
        List<RoleEntreprise> roleEntreprises = RoleEntrepriseImpl.retrieveAllroleEntreprises();
        assertThat(roleEntreprises).isNotNull();
        assertThat(roleEntreprises.size()).isEqualTo(3);
        //negative scenario
        given(RoleEntrepriseRepository.findAll()).willReturn(Collections.emptyList());
        roleEntreprises = RoleEntrepriseImpl.retrieveAllroleEntreprises();
        assertThat(roleEntreprises).isEmpty();
        assertThat(roleEntreprises.size()).isEqualTo(0);
    }

    @Test
    void roleEntrepriseDetailsTest() {
        given(RoleEntrepriseRepository.findById(1L)).willReturn(Optional.of(roleEntreprise1));
        RoleEntreprise saved = RoleEntrepriseImpl.roleEntrepriseDetails(roleEntreprise1.getIdRole());
        assertThat(saved).isNotNull();
    }

    @Test
    void ajoutRoleEntrepriseTest() {
        roleEntreprise3 = RoleEntreprise.builder().idRole(1L).idEntreprise(31L).idContact(43L).roleUser(RoleUser.ROLE_RESPONSABLETICKET).build();
        given(RoleEntrepriseRepository.save(roleEntreprise3)).willReturn(roleEntreprise3);
        RoleEntreprise save = RoleEntrepriseImpl.ajoutRoleEntreprise(roleEntreprise3);
        assertThat(save).isNotNull();
        System.out.println("ajoutRoleEntrepriseTest" + save);
    }

    @Test
    void retrievePropPerEntrepriseTest() {
        Contact prop = RoleEntrepriseImpl.retrievePropPerEntreprise(31L);
        assertThat(prop).isNotNull();
        System.out.println("retrievePropPerEntrepriseTest" + prop);
    }

    @Test
    void retrieveEntreprisePercontatcTest() {
        Entreprise entreprise = RoleEntrepriseImpl.retrieveEntreprisePercontatc(41L);
        assertThat(entreprise).isNotNull();
        System.out.println("retrieveEntreprisePercontatcTest" + entreprise);
    }

    @Test
    void retrievecontactsPerEntrepriseTest() {
        List<Contact> contacts = RoleEntrepriseImpl.retrievecontactsPerEntreprise(31L);
        assertThat(contacts).isNotNull();
        System.out.println("retrieveEntreprisePercontatcTest" + contacts.size());
    }

    @Test
    void retrieveEntreprisesPerPropTest() {
        Entreprise entreprise = RoleEntrepriseImpl.retrieveEntreprisesPerProp(41L);
        assertThat(entreprise).isNotNull();
        System.out.println("retrieveEntreprisesPerPropTest" + entreprise);
    }


}
