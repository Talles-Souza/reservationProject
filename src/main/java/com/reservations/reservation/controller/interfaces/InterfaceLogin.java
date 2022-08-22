package com.reservations.reservation.controller.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.reservations.reservation.model.entity.dto.LoginDTO;
import com.reservations.reservation.model.entity.dto.TokenDTO;
import com.reservations.reservation.model.entity.dto.UsersDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

public interface InterfaceLogin {

	// para segurança do end point sozinho, esse foi somente para teste
	//@SecurityRequirement(name = "Bearer Authentication")
	@Operation(summary = "Realizar Login", description = "Realizar login")
	@PostMapping
	public ResponseEntity<TokenDTO> auth(@RequestBody LoginDTO dto);
}