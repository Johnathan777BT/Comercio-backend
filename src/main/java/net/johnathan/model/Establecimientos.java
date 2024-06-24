package net.johnathan.model;

import java.sql.Date;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name = "establecimientos")
public class Establecimientos {

	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "TRIGGER_ESTABLECIMIENTO_ID")
	@SequenceGenerator(name = "TRIGGER_ESTABLECIMIENTO_ID", sequenceName = "TRIGGER_ESTABLECIMIENTO_ID")
	private Integer id_est;
	
	@NotNull
	@NotEmpty
	private String nombre_establecimiento;
	
	
	@NotNull
	@Min(1)
	private double ingresos;
	
	@NotNull
	@Min(1)
	private int nro_empleados;
	
	
	private Date fecha_act;
	
	private String usuario;

	
	public Establecimientos() {}
	
	public Establecimientos(Integer id_est, @NotNull @NotEmpty String nombre_establecimiento,
			@NotNull @Min(1) double ingresos, @NotNull @Min(1) int nro_empleados, Date fecha_act, String usuario) {
		super();
		this.id_est = id_est;
		this.nombre_establecimiento = nombre_establecimiento;
		this.ingresos = ingresos;
		this.nro_empleados = nro_empleados;
		this.fecha_act = fecha_act;
		this.usuario = usuario;
	}

	public Integer getId_est() {
		return id_est;
	}

	public void setId_est(Integer id_est) {
		this.id_est = id_est;
	}

	public String getNombre_establecimiento() {
		return nombre_establecimiento;
	}

	public void setNombre_establecimiento(String nombre_establecimiento) {
		this.nombre_establecimiento = nombre_establecimiento;
	}

	public double getIngresos() {
		return ingresos;
	}

	public void setIngresos(double ingresos) {
		this.ingresos = ingresos;
	}

	public int getNro_empleados() {
		return nro_empleados;
	}

	public void setNro_empleados(int nro_empleados) {
		this.nro_empleados = nro_empleados;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="FK_ID_COM")
	private Comerciantes comerciantes;
	
	@JsonBackReference(value="c")
	public Comerciantes getComerciantes() {
		return comerciantes;
	}

	public void setComerciantes(Comerciantes comerciantes) {
		this.comerciantes = comerciantes;
	}
	

	
}
