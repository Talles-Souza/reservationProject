package com.reservations.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservations.reservation.controller.interfaces.InterfaceUsers;
import com.reservations.reservation.exceptions.NoSuchElementFoundException;
import com.reservations.reservation.model.entity.dto.LoginDTO;
import com.reservations.reservation.model.entity.dto.TokenDTO;
import com.reservations.reservation.model.entity.dto.UsersDTO;
import com.reservations.reservation.service.TokenService;
import com.reservations.reservation.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/login")

//@CrossOrigin(origins = "", allowedHeaders = "")
//para todos os end da classe
//@SecurityRequirement(name = "bearerAuth")
//@Tag(name = "User", description = "The User API. Contains all the operations that can be performed on a user.")

public class LoginController implements InterfaceUsers {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	TokenService tokenService;

	@Autowired
	UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public ResponseEntity<TokenDTO> auth(@RequestBody LoginDTO dto) {
		LoginDTO dto1 = userService.insertAdminDefault(dto);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				dto1.getEmail(), dto1.getPassword());
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//         UserDetail user  = (UserDetail) authentication.getPrincipal();
		String token = tokenService.generateToken(authentication);
		TokenDTO tokenDTO = new TokenDTO("Bearer", token);
		return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
	}

	public ResponseEntity<List<UsersDTO>> findAllUsers() {
		List<UsersDTO> usersList = userService.findByAll();
		if (null == usersList)
			throw new NoSuchElementFoundException("Nenhum usuário encontrado.");
		else
			return new ResponseEntity<>(usersList, HttpStatus.OK);
	}

	public ResponseEntity<UsersDTO> findUsersById(@PathVariable Integer id) {
		UsersDTO clienteDTO = userService.findClienteById(id);
		if (null == clienteDTO)
			throw new NoSuchElementFoundException("Não foi encontrado o usuário com o id: " + id);
		else
			return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
	}

}
