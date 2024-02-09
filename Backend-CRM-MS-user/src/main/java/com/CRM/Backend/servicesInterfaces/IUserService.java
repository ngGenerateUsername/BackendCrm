package com.CRM.Backend.servicesInterfaces;

import java.util.List;

import com.CRM.Backend.entities.Contact;
import com.CRM.Backend.entities.dto.*;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    public List<Contact> retrieveAllcontacts();
    public String verify(String verificationCode);
    public String getEncodedPassword(String password) ;
    public Contact updatePassword(DtoContactUpdatePassword cc);
    public Contact updateProfile(DtoContactUpdateProfile cc);
    public Contact ActivDeactivContact(DtoUserChangeStatus DtoUserChangeStatus);
    public Contact createverificationCodeForgetPassword(String mail , String username);
    public boolean forgetpassword(String verificationCodeForgetPassword,String password);
    public Contact profileContact(Long id);
    public Contact findByUsername(String id);
    public Contact findByMail(String id);
    public Contact UpdateImage(DtoContactUpdateImage cc);
    public ResponseEntity<?> registerUser(SignupRequest signUpRequest);
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
    public String RefreshJwtToken(Long id);
    public Boolean updateUsernameORMail(DtoContactUpdateUserameOrMail DtoContactUpdateUserameOrMail);
    public List<Contact> AllContacts();
}
