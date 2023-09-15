package com.latam.cmz.hotelalura.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="Tipos_Usuarios")
public class TipoUsuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "BIGINT UNSIGNED")
	private Long Id;
	@Column(length = 20)
	private String Nombre;
	
	public TipoUsuario() {}
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		this.Id=id;
	}
	
	
}
