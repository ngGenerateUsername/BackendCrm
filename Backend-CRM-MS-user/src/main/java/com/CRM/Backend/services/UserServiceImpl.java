package com.CRM.Backend.services;

import java.util.*;
import java.util.stream.Collectors;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.entities.dto.*;
import com.CRM.Backend.repositories.UserRepository;
import com.CRM.Backend.repositories.RoleRepository;
import com.CRM.Backend.security.jwt.JwtUtils;
import com.CRM.Backend.security.services.UserDetailsImpl;
import com.CRM.Backend.servicesInterfaces.IUserService;
import com.CRM.Backend.servicesInterfaces.IEntrepriseService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import net.bytebuddy.utility.RandomString;


@Service
public class UserServiceImpl implements IUserService {


	@Value("${bezkoder.app.jwtSecret}")
	private String jwtSecret;

	@Value("${bezkoder.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;


	@Autowired
	IEntrepriseService IentrepriseService;

	@Autowired
	com.CRM.Backend.servicesInterfaces.IRoleEntrepriseService IRoleEntrepriseService;

	public List<Contact> retrieveAllcontacts() {
		List<Contact> Contacts = userRepository.findAll();

		return Contacts;
	}

	public String verify(String verificationCode) {
		Contact user = userRepository.findByVerificationCode(verificationCode);
		if (user == null || user.getStatusUser() == StatusUser.ACTIF) {
			return "verify_fail";
		} else {
			user.setVerificationCode(null);
			user.setStatusUser(StatusUser.ACTIF);
			user.setDateModification(new Date());
			userRepository.save(user);
			return "verify_success";
		}
	}

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

	public Contact updatePassword(DtoContactUpdatePassword cc) {
		try {
			Contact ccc = userRepository.findById(cc.getIdUser()).get();
			ccc.setPassword(getEncodedPassword(cc.getPassword()));
			ccc.setDateModification(new Date());
			return userRepository.save(ccc);
		} catch (Exception e) {
			return null;
		}
	}


	public Contact ActivDeactivContact(DtoUserChangeStatus DtoUserChangeStatus) {
		try {
			Contact ccc = userRepository.findById(Long.valueOf(DtoUserChangeStatus.getIdUser())).get();
			ccc.setStatusUser(DtoUserChangeStatus.getStatusUser());
			ccc.setDateModification(new Date());
			return userRepository.save(ccc);
		} catch (Exception e) {
			return null;
		}
	}

	public Contact updateProfile(DtoContactUpdateProfile DtoContactUpdateProfile) {
		try {
			Contact ccc = userRepository.findById(DtoContactUpdateProfile.getIdUser()).get();
			if (DtoContactUpdateProfile.getAdresse() != null) {
				ccc.setAdresse(DtoContactUpdateProfile.getAdresse());
				ccc.setDateModification(new Date());
			}
			if (DtoContactUpdateProfile.getNom() != null) {
				ccc.setNom(DtoContactUpdateProfile.getNom());
				ccc.setDateModification(new Date());
			}
			if (DtoContactUpdateProfile.getPrenom() != null) {
				ccc.setPrenom(DtoContactUpdateProfile.getPrenom());
				ccc.setDateModification(new Date());
			}
			if (DtoContactUpdateProfile.getNumTel() != null) {
				ccc.setNumTel(DtoContactUpdateProfile.getNumTel());
				ccc.setDateModification(new Date());
			}
			return userRepository.save(ccc);
		} catch (Exception e) {
			return null;
		}
	}

	public Contact createverificationCodeForgetPassword(String mail, String username) {
		if (username != null) {
			Contact user = userRepository.findByUsername(username);
			if (user == null) {
				return null;
			} else {
				try {
					String randomCode = RandomString.make(64);
					user.setVerificationCodeForgetPassword(randomCode);
					user.setDateModification(new Date());
					userRepository.save(user);
					return user;
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		} else {
			Contact user = userRepository.findByMail(mail);
			if (user == null) {
				return null;
			} else {
				try {
					String randomCode = RandomString.make(64);
					user.setVerificationCodeForgetPassword(randomCode);
					user.setDateModification(new Date());
					userRepository.save(user);
					return user;
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}

		return null;
	}

	public boolean forgetpassword(String verificationCodeForgetPassword, String password) {
		Contact user = userRepository.findByVerificationCodeForgetPassword(verificationCodeForgetPassword);
		if (user == null) {
			return false;
		} else {
			try {
				user.setVerificationCodeForgetPassword(null);
				user.setPassword(getEncodedPassword(password));
				user.setDateModification(new Date());
				userRepository.save(user);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}

	public Contact profileContact(Long id) {
		return userRepository.findById(id).get();
	}

	public Contact findByUsername(String id) {
		return userRepository.findByUsername(id);
	}

	public Contact findByMail(String id) {
		return userRepository.findByMail(id);
	}

	public Contact UpdateImage(DtoContactUpdateImage cc) {
		try {
			Contact ccc = userRepository.findById(cc.getIdUser()).get();
			ccc.setImage(cc.getImage());
			ccc.setDateModification(new Date());
			return userRepository.save(ccc);
		} catch (Exception e) {
			return null;
		}
	}

	public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByMail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		Contact user = new Contact();
		user.setUsername(signUpRequest.getUsername());
		user.setMail(signUpRequest.getEmail());
		user.setPassword(encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		System.out.println(strRoles);
			strRoles.forEach(role -> {
				switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(RoleUser.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found.22"));
						roles.add(adminRole);
						break;
					case "COMMERCIAL":
						Role modRole = roleRepository.findByName(RoleUser.ROLE_COMMERCIAL)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found33."));
						roles.add(modRole);
						break;
					case "PROPRIETAIRE":
						Role PROPRIETAIRERole = roleRepository.findByName(RoleUser.ROLE_PROPRIETAIRE)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found33."));
						roles.add(PROPRIETAIRERole);
						break;
					case "RESPONSABLETICKET":
						Role RESPONSABLETICKETRole = roleRepository.findByName(RoleUser.ROLE_RESPONSABLETICKET)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found33."));
						roles.add(RESPONSABLETICKETRole);
						break;
					case "CONTACT":
						Role CONTACTRole = roleRepository.findByName(RoleUser.ROLE_CONTACT)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found33."));
						roles.add(CONTACTRole);
						break;
					default:
						new RuntimeException("Error: Role is not found44.");
				}
			});
		String randomCode = RandomString.make(64);
		user.setVerificationCode(randomCode);
		user.setDateCreation(new Date());
		user.setImage("user.png");
		user.setStatusUser(StatusUser.INVITED);
		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(user.getIdUser());
	}

	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		Contact u = userRepository.findById(userDetails.getId()).get();
		if (u.getStatusUser() == StatusUser.INVITED) {
			return ResponseEntity.ok(new JwtResponse(null, null, null, null, null));
		}
		if (u.getStatusUser() == StatusUser.DEACTIVATED) {
			return ResponseEntity.ok(new JwtResponse(null, null, null, null, null));
		}
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getEmail(),
				roles));
	}

	public String RefreshJwtToken(Long id) {
		Contact user = userRepository.findById(id).get();
		Role role = user.getRoles().stream().findFirst().get();
		String nameRole = "["+role.getName().toString()+"]";
		return Jwts.builder().setId(user.getIdUser().toString()).setAudience(nameRole).setSubject(user.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();

	}

	public Boolean updateUsernameORMail(DtoContactUpdateUserameOrMail DtoContactUpdateUserameOrMail) {
		try {
			Contact ccc = userRepository.findById(DtoContactUpdateUserameOrMail.getIdUser()).get();
			if (DtoContactUpdateUserameOrMail.getMail() != null) {
				if(findByMail(DtoContactUpdateUserameOrMail.getMail()) == null){
					ccc.setMail(DtoContactUpdateUserameOrMail.getMail());
					userRepository.save(ccc);
					return true;
				}
				else {return false;}
			}
			if (DtoContactUpdateUserameOrMail.getUsername() != null) {
				if(findByUsername(DtoContactUpdateUserameOrMail.getUsername()) == null){
					ccc.setUsername(DtoContactUpdateUserameOrMail.getUsername());
					userRepository.save(ccc);
					return true;
				}
				else {return false;}
			}
			return false;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Contact> AllContacts() {
		List<Contact> AllContacts = userRepository.findAll();
		List<Contact> contacts = new ArrayList<>();
		for (Contact c : AllContacts) {
		if(c.getRoles().iterator().next().getName().toString().equals("ROLE_CONTACT") ){
			contacts.add(c);
		}
		}
		return contacts;
	}

}








