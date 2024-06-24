package net.johnathan.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "rol")
public class Rol {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "rol-uuid")
	@SequenceGenerator(name = "rol-uuid", allocationSize = 1, sequenceName = "SEQ_ROL")
	private Integer id;
	
	@Column(nullable = false, length = 50, unique = true)
	private String nombre_rol;

	public Rol() { }
	
	public Rol(String nombre) {
		this.nombre_rol = nombre;
	}
	
	public Rol(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return nombre_rol;
	}

	public void setName(String name) {
		this.nombre_rol = name;
	}

	@Override
	public String toString() {
		return this.nombre_rol;
	}
	
	
}
