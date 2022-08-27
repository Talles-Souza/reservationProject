package com.reservations.reservation.controller.interfaces;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.reservations.reservation.model.entity.dto.UsersDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

public interface InterfaceUsers {

	@GetMapping("/all")
	@SecurityRequirement(name = "Bearer Authentication")
	@Operation(summary = "Listar todos os Usuários", description = "Listagem de usuários")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Sintaxe de requisição mal formada, enquadramento de mensagem de requisição inválida"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado ou já existente") })
	public ResponseEntity<List<UsersDTO>> findAllUsers();

	@GetMapping("/{id}")
	@SecurityRequirement(name = "Bearer Authentication")
	@Operation(summary = "Retornar um usuário", description = "Usuário")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Buscado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Sintaxe de requisição mal formada ou enquadramento de mensagem de requisição inválida "),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado ou já existente") })
	public ResponseEntity<UsersDTO> findUsersById(@PathVariable Integer id);

	@PostMapping("/collaborator")
	@Operation(summary = "Salvar um usuário colaborador ", description = "Usuário")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Criado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Sintaxe de requisição mal formada, enquadramento de mensagem de requisição inválida "),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado ou já existente") })
	public ResponseEntity<UsersDTO> saveCollaborator(@RequestBody @Valid UsersDTO dto);

	@PutMapping("/collaborator")
	@Operation(summary = "Atualizar um usuário colaborador ", description = "Usuário")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Sintaxe de requisição mal formada, enquadramento de mensagem de requisição inválida "),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado ou já existente") })
	public ResponseEntity<UsersDTO> updateCollaborator(@RequestBody @Valid UsersDTO dto);

	@PostMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@SecurityRequirement(name = "Bearer Authentication")
	@Operation(summary = "Salvar um usuário Administrador ", description = "Usuário")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Criado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Sintaxe de requisição mal formada, enquadramento de mensagem de requisição inválida "),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado ou já existente") })
	public ResponseEntity<UsersDTO> saveAdmin(@RequestBody @Valid UsersDTO dto);

	@PutMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@SecurityRequirement(name = "Bearer Authentication")
	@Operation(summary = "Atualizar um usuário Administrador ", description = "Usuário")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Sintaxe de requisição mal formada, enquadramento de mensagem de requisição inválida "),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado ou já existente") })
	public ResponseEntity<UsersDTO> updateAdmin(@RequestBody @Valid UsersDTO dto);
	
	@DeleteMapping("/{id}")
	@SecurityRequirement(name = "Bearer Authentication")
	@Operation(summary = "Deletar um usuário", description = "Usuário")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Sintaxe de requisição mal formada ou enquadramento de mensagem de requisição inválida "),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado ou já existente") })
	public ResponseEntity<String> deleteUsersById(@PathVariable Integer id);
}
