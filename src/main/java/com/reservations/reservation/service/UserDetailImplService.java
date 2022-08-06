package com.reservations.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.reservations.reservation.model.entity.Users;
import com.reservations.reservation.repositories.UsersRepository;
import com.reservations.reservation.security.UserDetail;

@Service
public class UserDetailImplService implements UserDetailsService {

	@Autowired
	private UsersRepository usuarioRepository;

	public UserDetailImplService(UsersRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users usuario = usuarioRepository.findByEmail(username);
	
		return new UserDetail(usuario);
	}

	}

