package com.latam.cmz.hotelalura.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name="Datos_Personales")
public class DatoPersonal {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "BIGINT UNSIGNED")
	private Long Id;
	private String Nombre;
	private String Apellido;
	private LocalDate Fecha_de_nacimiento;
	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name = "Id_Nacionalidad", nullable = false)
	private Nacionalidad Nacionalidad;
	@Column(length = 50)
	private String Telefono;
	@Column(nullable = false, columnDefinition = "BIT DEFAULT 1")
	private Boolean Activo;
	
	public DatoPersonal() {}
	
	/**
	 * 
	 * @param nombre String
	 * @param apellido String
	 * @param fecha_de_nacimiento LocalDate
	 * @param nacionalidad Nacionalidad
	 * @param telefono String
	 */
	public DatoPersonal(String nombre, String apellido, LocalDate fecha_de_nacimiento,
			Nacionalidad nacionalidad, String telefono) {
		this.Nombre = nombre;
		this.Apellido = apellido;
		this.Fecha_de_nacimiento = fecha_de_nacimiento;
		this.Nacionalidad = nacionalidad;
		this.Telefono = telefono;
		this.Activo=true;
	}


	public String getNombre() {
		return this.Nombre;
	}
	public void setNombre(String nombre) {
		this.Nombre = nombre;
	}
	public String getApellido() {
		return this.Apellido;
	}
	public void setApellido(String apellido) {
		this.Apellido = apellido;
	}
	public LocalDate getFecha_de_nacimiento() {
		return this.Fecha_de_nacimiento;
	}
	public void setFecha_de_nacimiento(LocalDate fecha_de_nacimiento) {
		this.Fecha_de_nacimiento = fecha_de_nacimiento;
	}
	public Nacionalidad getId_Nacionalidad() {
		return this.Nacionalidad;
	}
	public void setId_Nacionalidad(Nacionalidad id_Nacionalidad) {
		this.Nacionalidad = id_Nacionalidad;
	}
	public String getTelefono() {
		return this.Telefono;
	}
	public void setTelefono(String telefono) {
		this.Telefono = telefono;
	}
	public Boolean getActivo() {
		return this.Activo;
	}
	public void setActivo(Boolean activo) {
		this.Activo = activo;
	}
	public Long getId() {
		return this.Id;
	}
	
	
	
	
	
}
