package net.johnathan.user.api;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.johnathan.user.Usuario;
import net.johnathan.user.UsuarioRepository;

@Service
@Transactional
public class UserService {
	@Autowired private UsuarioRepository repo;
	@Autowired private PasswordEncoder passwordEncoder;
	
	public Usuario save(Usuario user) {
		String rawPassword = user.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encodedPassword);
		
		return repo.save(user);
	}
}
