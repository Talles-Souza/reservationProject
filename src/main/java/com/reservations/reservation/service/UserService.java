package com.reservations.reservation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.reservations.reservation.emails.SendEmail;
import com.reservations.reservation.exceptions.NoSuchElementFoundException;
import com.reservations.reservation.messages.MessageEmail;
import com.reservations.reservation.model.entity.Roles;
import com.reservations.reservation.model.entity.Users;
import com.reservations.reservation.model.entity.dto.LoginDTO;
import com.reservations.reservation.model.entity.dto.UsersDTO;
import com.reservations.reservation.model.entity.enums.EProfile;
import com.reservations.reservation.model.entity.interfaces.Convert;
import com.reservations.reservation.repositories.RolesRepository;
import com.reservations.reservation.repositories.UsersRepository;

@Service
public class UserService implements Convert<UsersDTO, Users> {
	@Autowired
	UsersRepository userRepository;

	@Autowired
	RolesRepository profileRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	SendEmail sendEmail;

	public LoginDTO insertAdminDefault(LoginDTO dto) {
		Users user = userRepository.findByEmail(dto.getEmail());
		if (user.getId() == 1) {
			List<Roles> roles = new ArrayList<>();
			roles.add(profileRepository.findByNome(EProfile.ROLE_ADMIN).get());
			user.setRoles(roles);
			profileRepository.saveAll(user.getRoles());
			
			sendEmail.send(user.getEmail(), user.getName(), MessageEmail.messageLogin(user));
			
			return dto;
		} else {
			return dto;
		}
		
		

	}
	
	public List<Users> findByAll1()  {
		List<Users> users = userRepository.findAll();
		if (users.isEmpty()) {
			throw new NoSuchElementFoundException("Não há usuários cadastrados");
		}
		return users;
	}

	public List<UsersDTO> findByAll() {
		List<Users> usersEntity = userRepository.findAll();
		List<UsersDTO> usersDTO = new ArrayList<>();

		for (Users cliente : usersEntity) {
			usersDTO.add(toDTO(cliente));
		}

		return usersDTO;
	}

	public UsersDTO findClienteById(Integer id) {
		return userRepository.findById(id).isPresent() ? toDTO(userRepository.findById(id).get())
				: null;
	}

	@Override
	public Users toEntity(UsersDTO dto) {
		Users users = new Users();
		users.setName(dto.getName());
		users.setEmail(dto.getEmail());
		users.setPassWord(passwordEncoder.encode(dto.getPassword()));
		users.setCpf(dto.getCpf());
		List<Roles> perfis = new ArrayList<>();
		perfis.add(profileRepository.findByNome(EProfile.ROLE_ADMIN).get());
		users.setRoles(perfis);
		profileRepository.saveAll(users.getRoles());
        return users;
	}

	@Override
	public UsersDTO toDTO(Users entity) {
		UsersDTO users = new UsersDTO();
		users.setName(entity.getName());
		users.setEmail(entity.getEmail());
		users.setPassword(passwordEncoder.encode(entity.getPassWord()));
		users.setCpf(entity.getCpf());
		users.setRoles(entity.getRoles());
		return users;
	}
	
	
}
