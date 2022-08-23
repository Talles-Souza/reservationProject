package com.reservations.reservation.messages;

import java.time.LocalDate;

import com.reservations.reservation.model.entity.Users;

public class MessageRegister {
	
	public static String messageNewUser (Users user) {
		StringBuilder sBuilder = new StringBuilder();
		         sBuilder.append(
			  	"<p>\r\n Olá " + user.getName() + "! Seja muito bem-vindo(a) ao Reservation. "
				+ "Aqui você reserva a sua mesa ou sala, de forma rápida e eficiente. </p>"
				+"</br>"
				+"<img src=https://st2.depositphotos.com/1005147/5192/i/450/depositphotos_51926417-stock-photo-hands-holding-the-sun-at.jpg/>"
				+"<p>==============================================================</p>"
				+"<p>Confirmamos a criação de uma conta em nosso site no dia "+LocalDate.now()+"  com as seguintes informações : </p> "
				+"<p><strong>Nome : </strong>"+user.getName()+"</p>"
				+"<p><strong>Email : </strong>"+user.getEmail()+"</p>"
				+"<p><strong>Cpf : </strong>"+user.getCpf()+"</p>"
				+"<p>==============================================================</p>"
				+"</br>"
				+"<p>Caso não tenha sido você que realizou o cadastro, porfavor entre em contato com a nossa equipe de suporte, que resolveremos seu problema.</p>");
			   
			   return sBuilder.toString();
				
	}

}
