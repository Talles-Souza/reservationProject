package com.reservations.reservation.service;

import com.reservations.reservation.model.entity.Users;

public class MessageEmail {

	public static String createTitle(Users user) {
		return
				user.getName() + "Seu login foi realizazdo com sucesso";
		}
	
	
	public static String messageLogin(Users user ) {
		return "Olá " + user.getName()
		+"! Seja bem-vindo novamente  ao nosso site . "
		+"Seus dados estarão logo abaixo :  \n\n" 
		+"================================================ \n" 
		+"Nome" 
		+"Email"
		+"Cpf"
		
	}
}
