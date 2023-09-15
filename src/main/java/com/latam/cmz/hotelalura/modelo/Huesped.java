package com.latam.cmz.hotelalura.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Table(name="Huespedes")
public class Huesped {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "BIGINT UNSIGNED")
	private Long Id;
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "Id_Datos_Personal", nullable = false, unique = true)
	private DatoPersonal DatoPersonal;
	@Column(nullable = false, columnDefinition = "BIT DEFAULT 1")
	private Boolean Activo;
	
	public Huesped() {}
	
	public Huesped(DatoPersonal datoPersonal) {
		this.DatoPersonal = datoPersonal;
		this.Activo=true;
	}

	public DatoPersonal getDatoPersonal() {
		return DatoPersonal;
	}
	public void setDatoPersonal(DatoPersonal datoPersonal) {
		this.DatoPersonal = datoPersonal;
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
