package net.codejava.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import net.johnathan.user.Rol;
import net.johnathan.user.Usuario;
import net.johnathan.user.UsuarioRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UsuarioRepositoryTests {

	@Autowired private UsuarioRepository repo;
	
	@Test
	public void testCreateUser() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode("nam2020");
		
		Usuario newUser = new Usuario("nam@codejava.net", password);
		Usuario savedUser = repo.save(newUser);
		
		assertThat(savedUser).isNotNull();
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testAssignRoleToUser() {
		Integer userId = 4;
		Integer roleId = 3;
		Usuario user = repo.findById(userId).get();
		user.addRole(new Rol(roleId));
		
		Usuario updatedUser = repo.save(user);
		assertThat(updatedUser.getRoles()).hasSize(1);
		
	}
}
