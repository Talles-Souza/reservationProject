package com.reservations.reservation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.reservations.reservation.model.entity.Roles;
import com.reservations.reservation.model.entity.Users;
import com.reservations.reservation.model.entity.dto.LoginDTO;
import com.reservations.reservation.model.entity.enums.EProfile;
import com.reservations.reservation.repositories.RolesRepository;
import com.reservations.reservation.repositories.UsersRepository;

@Service
public class UserService {
	@Autowired
	UsersRepository usuarioRepository;

	@Autowired
	RolesRepository perfilRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	SendEmail sendEmail;

	public LoginDTO insertAdminDefault(LoginDTO dto) {
		Users user = usuarioRepository.findByEmail(dto.getEmail());
		if (user.getId() == 1) {
			List<Roles> roles = new ArrayList<>();
			roles.add(perfilRepository.findByNome(EProfile.ROLE_ADMIN).get());
			user.setRoles(roles);
			perfilRepository.saveAll(user.getRoles());
			
			sendEmail.send(user.getEmail(), user.getName(), MessageEmail.messageLogin(user));
			
			return dto;
		} else {
			return dto;
		}

	}

}
