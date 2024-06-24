package net.johnathan.DTO;

import java.math.BigDecimal;
import java.sql.Date;


public class ComercianteDTO {

	
	private BigDecimal id_com;
	
	private String nombre;
	
	private String nombre_departamento;
	
	private String telefono;
	
	private String correo_electronico;
	
	private java.sql.Date fecha_registro;
	
	private BigDecimal estado;
	
	private BigDecimal ingresos;
	
	private BigDecimal nroest;

	public BigDecimal getId_com() {
		return id_com;
	}

	public void setId_com(BigDecimal id_com) {
		this.id_com = id_com;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nomnbre) {
		this.nombre = nomnbre;
	}

	public String getNombre_departamento() {
		return nombre_departamento;
	}

	public void setNombre_departamento(String nombre_departamento) {
		this.nombre_departamento = nombre_departamento;
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

	public BigDecimal getEstado() {
		return estado;
	}

	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}

	public BigDecimal getIngresos() {
		return ingresos;
	}

	public void setIngresos(BigDecimal ingresos) {
		this.ingresos = ingresos;
	}

	public BigDecimal getNroEst() {
		return nroest;
	}

	public void setNroEst(BigDecimal nro_est) {
		this.nroest = nro_est;
	}
	
	

	public ComercianteDTO(BigDecimal id_com, String nomnbre, String nombre_departamento, String telefono,String correo_electronico,
			Date fecha, BigDecimal estado, 
			BigDecimal ingresos, BigDecimal nro_est) {
		
		this.id_com = id_com;
		this.nombre = nomnbre;
		this.nombre_departamento = nombre_departamento;
		this.telefono = telefono;
		this.correo_electronico = correo_electronico;
		this.fecha_registro = fecha;
		this.estado = estado;
		this.ingresos = ingresos;
		this.nroest = nro_est;
	}
	
	public ComercianteDTO() {
	
	}
	
	
}
