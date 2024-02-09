package com.CRM.Backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import java.util.*;

import com.CRM.Backend.entities.Entreprise;
import com.CRM.Backend.repositories.EntrepriseRepository;
import com.CRM.Backend.services.EntrepriseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EntrepriseServiceImplTest {

    @Mock
    private EntrepriseRepository EntrepriseRepository;

    @InjectMocks
    private EntrepriseServiceImpl EntrepriseServiceImpl;

    private Entreprise entreprise1;

    @BeforeEach
    public void setup() {
        entreprise1 = Entreprise.builder().idUser(31L).build();
    }

    @DisplayName("JUnit test")
    @Test
    void retrieveAllEntreprisesTest() {
                    Entreprise entreprise2 = Entreprise.builder().idUser(32L).build();

                    given(EntrepriseRepository.findAll()).willReturn(new ArrayList<>(Arrays.asList(entreprise1, entreprise2)));
                    List<Entreprise> Entreprises = EntrepriseServiceImpl.retrieveAllentreprises();
                    assertThat(Entreprises).isNotNull();
                    assertThat(Entreprises.size()).isEqualTo(2);
                    //negative scenario
                    given(EntrepriseRepository.findAll()).willReturn(Collections.emptyList());
                    Entreprises = EntrepriseServiceImpl.retrieveAllentreprises();
                    assertThat(Entreprises).isEmpty();
                    assertThat(Entreprises.size()).isEqualTo(0);
    }

    @Test
    void retrieveEntrepriseTest() {
        given(EntrepriseRepository.findById(31L)).willReturn(Optional.of(entreprise1));
        Entreprise saved = EntrepriseServiceImpl.EentrepriseDetails(entreprise1.getIdUser());
        assertThat(saved).isNotNull();
    }

    @Test
    void addEntrepriseTest() {
        given(EntrepriseRepository.save(entreprise1)).willReturn(entreprise1);
        Entreprise save = EntrepriseServiceImpl.ajoutEentreprise(entreprise1);
        assertThat(save).isNotNull();
    }

    @Test
    void updateEntrepriseTest() {
        given(EntrepriseRepository.save(entreprise1)).willReturn(entreprise1);
        entreprise1.setNomEntreprise("F321");
        System.out.println(entreprise1.getIdUser());
        System.out.println(entreprise1.getNomEntreprise());
        System.out.println(entreprise1.toString());
        //Entreprise updated = EntrepriseServiceImpl.updateEntreprise(entreprise1);
       // assertThat(updated.getNomEntreprise()).isEqualTo("F321");
    }


}
