package com.latam.cmz.hotelalura.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="Nacionalidades")
public class Nacionalidad {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "BIGINT UNSIGNED")
	private Long Id;
	@Column(length = 40)
	private String Pais;
	@Column(length = 40)
	private String Gentilicio;
	@Column(length = 10)
	private String Iso;
	
	public Nacionalidad() {}
	
	public String getPais() {
		return this.Pais;
	}
	public void setPais(String pais) {
		this.Pais = pais;
	}
	public String getGentilicio() {
		return this.Gentilicio;
	}
	public void setGentilicio(String gentilicio) {
		this.Gentilicio = gentilicio;
	}
	public String getIso() {
		return this.Iso;
	}
	public void setIso(String iso) {
		this.Iso = iso;
	}
	public Long getId() {
		return this.Id;
	}
	
	
}
