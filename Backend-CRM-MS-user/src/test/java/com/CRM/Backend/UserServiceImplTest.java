package com.CRM.Backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.CRM.Backend.entities.Contact;
import com.CRM.Backend.entities.StatusUser;
import com.CRM.Backend.entities.dto.SignupRequest;
import com.CRM.Backend.repositories.UserRepository;
import com.CRM.Backend.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository UserRepository;
    
    @InjectMocks
    private UserServiceImpl ContactServiceImpl;

    private Contact contact1;

    @BeforeEach
    public void setup() {
         contact1 = Contact.builder()
                 .idUser(50L)
                 .mail("test1@test.test")
    			.username("test1")
    			.password("test1")
    			.build();
    }
    
    @DisplayName("JUnit test")
    @Test
    void retrieveAllcontactsTest() {
        Contact contact2 = Contact.builder()
                .idUser(52L)
                .mail("test2@test.test")
                .username("test2")
                .password("test2")
                .build();

                    given(UserRepository.findAll()).willReturn(new ArrayList<>(Arrays.asList(contact1, contact2)));
                    List<Contact> contacts = ContactServiceImpl.retrieveAllcontacts();
                    assertThat(contacts).isNotNull();
                    assertThat(contacts.size()).isEqualTo(2);
                    //negative scenario
                    given(UserRepository.findAll()).willReturn(Collections.emptyList());
                    contacts = ContactServiceImpl.retrieveAllcontacts();
                    assertThat(contacts).isEmpty();
                    assertThat(contacts.size()).isEqualTo(0);
    }

    @Test
    void addFournisseurTest() {
        SignupRequest s = SignupRequest.builder()
                .email("test@test.test")
                .password("test")
                .username("user")
                .role(Collections.singleton("COMMERCIAL"))
                .build();
        given(UserRepository.save(contact1)).willReturn(contact1);
        ResponseEntity<?> save = ContactServiceImpl.registerUser(s);
        assertThat(save).isNotNull();
    }

}
