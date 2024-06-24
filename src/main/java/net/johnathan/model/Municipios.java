package net.johnathan.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name = "municipios")
public class Municipios {

	@Id 	
	private Integer id_municipio;
	
	private String nombre_municipio;
	
	private boolean estado;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="departamento_id")
	private Departamentos departamento;

	
	
	
	public Municipios(Integer id_municipio, String nombre_municipio, boolean estado, Departamentos departamento) {
		super();
		this.id_municipio = id_municipio;
		this.nombre_municipio = nombre_municipio;
		this.estado = estado;
		this.departamento = departamento;
	}

	public Integer getId_municipio() {
		return id_municipio;
	}

	public void setId_municipio(Integer id_municipio) {
		this.id_municipio = id_municipio;
	}

	public String getMunicipio() {
		return nombre_municipio;
	}

	public void setMunicipio(String municipio) {
		this.nombre_municipio = municipio;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@JsonBackReference(value="b")
	public Departamentos getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamentos departamento) {
		this.departamento = departamento;
	}
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "municipios")
    private List<Comerciantes> ListCom=new ArrayList<>();
	
	@JsonManagedReference(value="z")
	public List<Comerciantes> getListCom() {
		return ListCom;
	}

	public void setListCom(List<Comerciantes> listCom) {
		ListCom = listCom;
	}
	
}
