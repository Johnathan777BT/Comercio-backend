package net.johnathan.DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ComercianteReportDTO {


	
	private BigDecimal id_com;
	
	private String nombre;
	
	private String nombre_departamento;
	
	private String nombre_municipio;
	
	private String telefono;
	
	private String correo_electronico;
	
	private Timestamp fecha_registro;
	
	private BigDecimal estado;
	
	private BigDecimal ingresos;
	
	private BigDecimal nro_empleados;
	
	private BigDecimal nro_establecimiento;

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

	public Timestamp getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Timestamp fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public BigDecimal isEstado() {
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

	public BigDecimal getNro_empleados() {
		return nro_empleados;
	}

	public String getNombre_municipio() {
		return nombre_municipio;
	}

	public void setNombre_municipio(String nombre_municipio) {
		this.nombre_municipio = nombre_municipio;
	}

	public BigDecimal getNro_establecimiento() {
		return nro_establecimiento;
	}

	public void setNro_establecimiento(BigDecimal nro_establecimiento) {
		this.nro_establecimiento = nro_establecimiento;
	}

	public BigDecimal getEstado() {
		return estado;
	}

	public void setNro_empleados(BigDecimal nro_empleados) {
		this.nro_empleados = nro_empleados;
	}

	public ComercianteReportDTO(BigDecimal id_com, String nombre, String nombre_departamento, String nombre_municipio,
			String telefono, String correo_electronico, Timestamp fecha_registro, BigDecimal estado,
			BigDecimal ingresos, BigDecimal nro_empleados, BigDecimal nro_establecimiento) {
		super();
		this.id_com = id_com;
		this.nombre = nombre;
		this.nombre_departamento = nombre_departamento;
		this.nombre_municipio = nombre_municipio;
		this.telefono = telefono;
		this.correo_electronico = correo_electronico;
		this.fecha_registro = fecha_registro;
		this.estado = estado;
		this.ingresos = ingresos;
		this.nro_empleados = nro_empleados;
		this.nro_establecimiento = nro_establecimiento;
	}
	public ComercianteReportDTO() {}
	
}
