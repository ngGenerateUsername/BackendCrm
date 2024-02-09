package com.CRM.Backend.entities.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequest {
	@NotBlank
	private String username;

	@NotBlank
	private String password;


}
