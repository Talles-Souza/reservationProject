package com.reservations.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservations.reservation.model.entity.dto.LoginDTO;
import com.reservations.reservation.model.entity.dto.TokenDTO;
import com.reservations.reservation.service.TokenService;
import com.reservations.reservation.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
//@CrossOrigin(origins = "", allowedHeaders = "")
@RequestMapping("/login")

//para todos os end da classe
//@SecurityRequirement(name = "bearerAuth")
//@Tag(name = "User", description = "The User API. Contains all the operations that can be performed on a user.")

public class LoginController {
	@Autowired
	AuthenticationManager authenticationManager;
  
	@Autowired
	TokenService tokenService;

	@Autowired
	UserService usuarioService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	//para segurança do end point sozinho, esse foi somente para teste
	@SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    public ResponseEntity<TokenDTO> auth(@RequestBody LoginDTO dto) {
		 LoginDTO dto1 = usuarioService.insertAdminDefault(dto);
	     UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(dto1.getEmail(), dto1.getPassword());
         Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//         UserDetail user  = (UserDetail) authentication.getPrincipal();
         String token = tokenService.generateToken(authentication);
         TokenDTO tokenDTO = new TokenDTO("Bearer", token);
        return  new ResponseEntity<>(tokenDTO, HttpStatus.OK);
    }
	
	
	
}
