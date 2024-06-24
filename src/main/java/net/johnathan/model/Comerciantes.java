package net.johnathan.model;

import java.sql.Date;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "comerciantes")
public class Comerciantes {

	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "TRIGGER_COMERCIANTES_ID")
	@SequenceGenerator(name = "TRIGGER_COMERCIANTES_ID", sequenceName = "TRIGGER_COMERCIANTES_ID")
	private Integer id_com;
	
	@NotNull
	@NotEmpty
	private String nombre;
	
	@ManyToOne(fetch = FetchType.EAGER) //  cascade=CascadeType.REFRESH)
	@JoinColumn(name="ID_MUNICIPIO")
	//@NotNull
	private Municipios municipios;
	
	@ManyToOne(fetch = FetchType.EAGER) // cascade=CascadeType.REFRESH)
	@JoinColumn(name="DEPARTAMENTO_ID")
	//@NotNull	
	private Departamentos departamentos;
	
	private String telefono;
	
	@Email(message = "Invalid email address")
	private String correo_electronico;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date fecha_registro;
	
	@NotNull
	private boolean estado;
	
	private Date fecha_act;
	
	private String usuario;

	public Comerciantes() {}
	
	
	public Comerciantes(@NotNull @NotEmpty String nombre, Departamentos departamento, Municipios municipio, String telefono,
			String correo_electronico,@NotNull Date fecha_registro, @NotNull boolean estado, Date fecha_act, String usuario) {
		
		this.nombre = nombre;
		this.departamentos = departamento;
		this.municipios = municipio;
		this.telefono = telefono;
		this.correo_electronico = correo_electronico;
		this.fecha_registro = fecha_registro;
		this.estado = estado;
		this.fecha_act = fecha_act;
		this.usuario = usuario;
	}

	public Integer getId_com() {
		return id_com;
	}
	
	public void setId_com(Integer id) {
		this.id_com=id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@JsonBackReference(value="a")
	public Departamentos getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(Departamentos departamento) {
		this.departamentos = departamento;
	}

	
	@JsonBackReference(value="z")
	public Municipios getMunicipios() {
		return municipios;
	}

	public void setMunicipios(Municipios municipio) {
		this.municipios = municipio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo_electronico() {
		return correo_electronico;
	}

	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}

	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Date getFecha_act() {
		return fecha_act;
	}

	public void setFecha_act(Date fecha_act) {
		this.fecha_act = fecha_act;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "comerciantes", cascade = CascadeType.ALL)
	private Set<Establecimientos> getListEstablecimientos= new HashSet<>();
	
	
	@JsonManagedReference(value="c")
	public Set<Establecimientos> getListComerciantes() {
		return getListEstablecimientos;
	}


	public void setListComerciantes(Set<Establecimientos> listComerciantes) {
		getListEstablecimientos = listComerciantes;
	}
	

	
}
