package com.reservations.reservation.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.reservations.reservation.model.entity.Users;
import com.reservations.reservation.security.UserDetail;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Service
public class TokenService {
	@Value("${jwt.secret}")
	private String jwtSecret;

	public String generateToken(Authentication authentication) {
		UserDetail usuario = (UserDetail) authentication.getPrincipal();

		String tokenJwt = Jwts.builder().setIssuer("IRS").setIssuer("T2M-SRM").setSubject(usuario.getUsername())
				.claim("id", usuario.getId()).claim("name", usuario.getName()).claim("email", usuario.getEmail())
				.claim("roles", usuario.getRoles()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
		System.out.println(usuario.getName());
		System.out.println(usuario.getId());

		return tokenJwt;
	}

	public String generateTokenWithUserData(Users usuario) {
		String tokenJwt = Jwts.builder().setIssuer("IRS").setIssuer("T2M-SRM").setSubject(usuario.getName())
				.claim("id", usuario.getId()).claim("name", usuario.getName()).claim("email", usuario.getEmail())
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, jwtSecret).compact();

		return tokenJwt;
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public Integer extractIdFromToken(String token) {
		Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		// Integer id = Integer.valueOf(body.getSubject());
		Integer id = Integer.valueOf(body.get("id").toString());
		return id;
	}
}
