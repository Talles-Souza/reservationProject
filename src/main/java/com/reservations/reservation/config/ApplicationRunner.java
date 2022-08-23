package com.reservations.reservation.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.reservations.reservation.model.entity.Roles;
import com.reservations.reservation.model.entity.Users;
import com.reservations.reservation.model.entity.enums.EProfile;
import com.reservations.reservation.repositories.RolesRepository;
import com.reservations.reservation.repositories.UsersRepository;

@Configuration
public class ApplicationRunner implements CommandLineRunner {

	@Autowired
	RolesRepository perfilRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		// INICIANDO ROLES NA TABELA PERFIL
		List<Roles> perfis = perfilRepository.findAll();
		if (perfis.isEmpty()) {
			Roles usuario = new Roles(EProfile.ROLE_COLABORADOR);
			Roles admin = new Roles(EProfile.ROLE_ADMIN);

			perfilRepository.saveAll(Arrays.asList(admin, usuario));
			
		}
		
		
		//	USUARIO DEFAULT
		
		List<Users> userDefault = usersRepository.findAll();
		if(userDefault.isEmpty()) {
			Users user = new Users();
			user.setId(1);
			user.setName("Admin");
			user.setEmail("admin@mail.com");
			user.setPassWord(passwordEncoder.encode("1234"));
			user.setCpf(null);
			usersRepository.save(user);
			List<Roles> roles = new ArrayList<>();
			roles.add(perfilRepository.findByNome(EProfile.ROLE_ADMIN).get());
			user.setRoles(roles);
			perfilRepository.saveAll(user.getRoles());
			System.out.println(EProfile.ROLE_COLABORADOR.ordinal());
			System.out.println(EProfile.ROLE_ADMIN);
			
		}
		

	}

}
