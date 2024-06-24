package net.johnathan.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
	@SequenceGenerator(name = "system-uuid", allocationSize = 1, sequenceName = "SEQ_USUARIOS")
	private Integer idu;
	
	@Email(message = "Invalid email address")
	@Column(nullable = false, length = 50, unique = true)
	@Email @Length(min = 5, max = 50)
	private String email;
	
	@Column(nullable = false, length = 64)
	@Length(min = 5, max = 64)
	private String password;

	@ManyToMany
	@JoinTable(
		name = "users_roles",
		joinColumns = @JoinColumn(name = "user_idu"),
		inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Rol> roles = new HashSet<>();
	
	public Usuario() { }
	
	public Usuario(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public Integer getId() {
		return idu;
	}

	public void setId(Integer id) {
		this.idu = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Rol role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
	public void addRole(Rol role) {
		this.roles.add(role);
	}
	
}
