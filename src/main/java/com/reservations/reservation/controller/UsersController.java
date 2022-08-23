package com.reservations.reservation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservations.reservation.controller.interfaces.InterfaceUsers;
import com.reservations.reservation.exceptions.NoSuchElementFoundException;
import com.reservations.reservation.model.entity.dto.UsersDTO;
import com.reservations.reservation.service.UserService;

@RestController
@RequestMapping("/users")

public class UsersController implements InterfaceUsers {

	@Autowired
	UserService userService;

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

	@Override
	public ResponseEntity<UsersDTO> saveCollaborator(@Valid UsersDTO dto) {
		UsersDTO user = null;
		try {
			user = userService.insertCollaborator(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	
	@Override
	public ResponseEntity<UsersDTO> saveAdmin(@Valid UsersDTO dto) {
		UsersDTO user = userService.insertAdmin(dto);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

}
