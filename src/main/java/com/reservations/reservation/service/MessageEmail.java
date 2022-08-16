package com.reservations.reservation.service;

import com.reservations.reservation.model.entity.Users;

public class MessageEmail {

	public static String createTitle(Users user) {
		return user.getName() + "Seu login foi realizazdo com sucesso";
	}

	public static String messageLogin(Users user ) {
		return "Olá " + user.getName()
		+"! Seja bem-vindo novamente  ao nosso site . "
		+"Seus dados estarão logo abaixo :  \n\n" 
		+"================================================ \n" 
		+"Nome : " + user.getName()+ "\n"
		+"Email : " +user.getEmail()+ "\n"
		+"Cpf : "  +user.getCpf() + "\n"
		+"================================================ \n\n"
		+"Caso não tenha sido o senhor que fez o login, por favor, entre em contato imediatamente com a nossa equipe . \n\n"
		
		+"Obs: Email test do site Reservatrion"
		;
		
	}
}
