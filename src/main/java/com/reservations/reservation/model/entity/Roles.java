package com.reservations.reservation.model.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import com.reservations.reservation.model.entity.enums.EProfile;

@Entity

public class Roles implements GrantedAuthority {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	@Enumerated (EnumType.STRING)
	private EProfile nome;

	public Roles(EProfile nome) {
		this.nome = nome;
	}

	
	public Roles() {
		super();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EProfile getNome() {
		return nome;
	}

	public void setNome(EProfile nome) {
		this.nome = nome;
	}

	@Override
	public String getAuthority() {
		return this.nome.toString();
	}

	public Object getPerfis() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String toString() {
		return "Roles [id=" + id + ", nome=" + nome + "]";
	}
	
	

}
