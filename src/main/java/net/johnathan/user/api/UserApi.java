package net.johnathan.user.api;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.johnathan.user.Usuario;

@RestController
public class UserApi {

	@Autowired private UserService service;
	
	@PutMapping("/users")
	public ResponseEntity<?> createUser(@RequestBody @Valid Usuario user) {
		Usuario createdUser = service.save(user);
		URI uri = URI.create("/users/" + createdUser.getId());
		
		UserDTO userDto = new UserDTO(createdUser.getId(), createdUser.getEmail());
		
		return ResponseEntity.created(uri).body(userDto);
	}
}
