package net.johnathan.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;

@Entity
@Table(name = "departamentos")
public class Departamentos {
	
	@Id
	@Column(name="departamento_id")
	private Integer id_departamento;
	
	private String nombre_departamento;

	public Departamentos() {}
	
	public Departamentos(Integer id_departamento, String departamento) {
		
		this.id_departamento = id_departamento;
		this.nombre_departamento = departamento;
	}

	public Integer getId_departamento() {
		return id_departamento;
	}

	public void setId_departamento(Integer id_departamento) {
		this.id_departamento = id_departamento;
	}

	public String getNombreDepartamento() {
		return nombre_departamento;
	}

	public void setNombreDepartamento(String departamento) {
		this.nombre_departamento = departamento;
	}
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "departamento", fetch = FetchType.LAZY)
    private List<Municipios> ListMunicipios=new ArrayList<>();

	@JsonManagedReference(value="b")
	public List<Municipios> getListMunicipios() {
		return ListMunicipios;
	}

	public void setListMunicipios(List<Municipios> listM) {
		ListMunicipios = listM;
	}
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "departamentos", fetch = FetchType.LAZY)
    private List<Comerciantes> ListCom=new ArrayList<>();

	
	@JsonManagedReference(value="a")
	public List<Comerciantes> getListCom() {
		return ListCom;
	}

	public void setListCom(List<Comerciantes> listCom) {
		ListCom = listCom;
	}
	
	
	

}

