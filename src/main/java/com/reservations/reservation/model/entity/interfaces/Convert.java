package com.reservations.reservation.model.entity.interfaces;

public interface Convert <DTO , ENTITY>{

	ENTITY toEntity(DTO dto );
	DTO toDTO(ENTITY entity );
}
