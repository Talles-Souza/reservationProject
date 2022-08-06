package com.reservations.reservation.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.reservations.reservation.model.entity.Users;
import com.reservations.reservation.repositories.UsersRepository;
import com.reservations.reservation.service.TokenService;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
	private final TokenService tokenService;
	private final UsersRepository usuarioRepository;
	
	public TokenAuthenticationFilter(TokenService tokenService, 
				UsersRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		String tokenHeader = extractTokenHeader(request);
		boolean tokenValido = tokenService.isTokenValid(tokenHeader);
		if(tokenValido) {
			this.autenticacao(tokenHeader);
		}
		filterChain.doFilter(request, response);
	}
	
	private String extractTokenHeader(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(null == token || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}
	
	private void autenticacao(String tokenHeader) {
		Integer id = tokenService.extractIdFromToken(tokenHeader);
		Optional<Users> optionalUsuario = usuarioRepository.findById(id);
		
		if(optionalUsuario.isPresent()) {
			Users usuario = optionalUsuario.get();
			
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
					new UsernamePasswordAuthenticationToken(usuario, null, usuario.getRoles());
			SecurityContextHolder.getContext()
				.setAuthentication(usernamePasswordAuthenticationToken);
		}
	}
	
}
