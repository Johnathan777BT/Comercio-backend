package net.codejava.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import net.johnathan.user.Rol;
import net.johnathan.user.RolRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RolRepositoryTests {
	@Autowired private RolRepository repo;
	
	@Test
	public void testCreateRoles() {
		Rol admin = new Rol("ROLE_ADMIN");
		Rol editor = new Rol("ROLE_EDITOR");
		Rol customer = new Rol("ROLE_CUSTOMER");
		
		repo.saveAll(List.of(admin, editor, customer));
		
		long count = repo.count();
		assertEquals(3, count);
	}
}
