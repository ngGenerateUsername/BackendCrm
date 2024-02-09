package com.CRM.Backend.controllers;

import javax.validation.Valid;
import com.CRM.Backend.entities.*;
import com.CRM.Backend.entities.dto.*;
import com.CRM.Backend.servicesInterfaces.IUserService;
import com.CRM.Backend.servicesInterfaces.IEntrepriseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	IUserService IUserService;

	@Autowired
	IEntrepriseService IentrepriseService;

	@Autowired
	com.CRM.Backend.servicesInterfaces.IRoleEntrepriseService IRoleEntrepriseService;



	@ApiOperation(value = "Sign in")
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return IUserService.authenticateUser(loginRequest);
	}

	@ApiOperation(value = "Sign up")
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return IUserService.registerUser(signUpRequest);
	}

	@ApiOperation(value = "Changer mot de passe")
	@PutMapping("/newPassword")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
		public Contact updatePassword(@Valid @RequestBody DtoContactUpdatePassword cc) {
		return IUserService.updatePassword(cc);
    }

	@ApiOperation(value = "Modifier informations de profile contact")
	@PutMapping("/updateProfile")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE')")
	public Contact updateProfile(@Valid @RequestBody DtoContactUpdateProfile cc) {
	return IUserService.updateProfile(cc);
	}

	@ApiOperation(value = "Ajouter entreprise")
	@PostMapping("/ajoutEntreprise")
	@PreAuthorize("hasRole('ADMIN')")
    public Entreprise ajoutEentreprise(@Valid @RequestBody Entreprise e)
	{return IentrepriseService.ajoutEentreprise(e);
	}

	@ApiOperation(value = "Affecter un user a une entreprise")
	@PutMapping("/ajoutRole_entreprise")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE')")
    public RoleEntreprise ajoutRole_entreprise(@Valid @RequestBody RoleEntreprise e)
	{return IRoleEntrepriseService.ajoutRoleEntreprise(e);
	}

	@ApiOperation(value = "Modifier informations d'une entreprise")
    @PutMapping("/ModifierEntreprise")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE')")
    public Entreprise ModifierEntreprise(@Valid @RequestBody DtoEntreprise e)
	{return IentrepriseService.updateEntreprise(e);
	}

	@ApiOperation(value = "Affecuter ou changer une image de profile")
	@PutMapping("/ModifierImage")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
	public Contact ModifierImage(@Valid @RequestBody DtoContactUpdateImage c)
	{return IUserService.UpdateImage(c);
	}

	@ApiOperation(value = "RefreshJwtToken")
	@GetMapping("/RefreshJwtToken")
	public String RefreshJwtToken(@Param("id") Long id) {
		return IUserService.RefreshJwtToken(id);
	}

	@ApiOperation(value = "Modifier UserName Ou Mail")
	@PutMapping("/updateUsernameORMail")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
	//@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE')")
	public Boolean updateUsernameORMail(@Valid @RequestBody DtoContactUpdateUserameOrMail cc) {
		return IUserService.updateUsernameORMail(cc);
	}

}
