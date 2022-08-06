package com.reservations.reservation.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservations.reservation.model.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	public Users findByEmail(String email);
	public Users findByCpf(String cpf);
	public Optional<Users> findByName(String name);
}
