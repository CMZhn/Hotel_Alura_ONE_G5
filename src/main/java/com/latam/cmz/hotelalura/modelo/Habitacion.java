package com.latam.cmz.hotelalura.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="Habitaciones")
public class Habitacion {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "BIGINT UNSIGNED")
	private Long Id;
	@Column(nullable = false, columnDefinition = "INT UNSIGNED DEFAULT 1")
	private Integer Capacidad;
	@Column(nullable = false, columnDefinition = "INT UNSIGNED DEFAULT 1")
	private Integer Calificacion;
	private String Descripcion;
	@Column(nullable = false)
	private BigDecimal Valor_fijo;
	@Column(nullable = false)
	private BigDecimal Valor_variable;
	@Column(nullable = false, columnDefinition = "BIT DEFAULT 1")
	private Boolean Activo;
	
	public Habitacion() {}
	
	public Integer getCapacidad() {
		return Capacidad;
	}
	public void setCapacidad(Integer capacidad) {
		Capacidad = capacidad;
	}
	public Integer getCalificacion() {
		return Calificacion;
	}
	public void setCalificacion(Integer calificacion) {
		Calificacion = calificacion;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public BigDecimal getValor_fijo() {
		return Valor_fijo;
	}
	public void setValor_fijo(BigDecimal valor_fijo) {
		Valor_fijo = valor_fijo;
	}
	public BigDecimal getValor_variable() {
		return Valor_variable;
	}
	public void setValor_variable(BigDecimal valor_variable) {
		Valor_variable = valor_variable;
	}
	public Boolean getActivo() {
		return Activo;
	}
	public void setActivo(Boolean activo) {
		Activo = activo;
	}
	public Long getId() {
		return Id;
	}
	
	
	
	
}
