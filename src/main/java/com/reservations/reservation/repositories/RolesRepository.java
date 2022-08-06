package com.reservations.reservation.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservations.reservation.model.entity.Roles;
import com.reservations.reservation.model.entity.enums.EProfile;

public interface RolesRepository extends JpaRepository<Roles, Integer> {

	public Optional<Roles> findByNome(EProfile nome);

}
