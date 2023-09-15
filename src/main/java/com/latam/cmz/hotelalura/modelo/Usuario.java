package com.latam.cmz.hotelalura.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Table(name="Usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "BIGINT UNSIGNED")
	private Long Id;
	@Column(unique = true, nullable = false, length = 50)
	private String Usuario;
	@Column(nullable = false, length = 100)
	private String Contraseña;
	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name = "Id_Tipo_Usuario", nullable = false)
	private TipoUsuario TipoUsuario;
	@OneToOne (fetch=FetchType.LAZY)
	@JoinColumn(name = "Id_Datos_Personal", nullable = false, unique = true)
	private DatoPersonal DatoPersonal;
	@Column(nullable = false, columnDefinition = "BIT DEFAULT 1")
	private Boolean Activo;
	
	
	public Usuario() {}
	
	public String getUsuario() {
		return this.Usuario;
	}
	public void setUsuario(String usuario) {
		this.Usuario = usuario;
	}
	public String getContraseña() {
		return this.Contraseña;
	}
	public void setContraseña(String contraseña) {
		this.Contraseña = contraseña;
	}
	public TipoUsuario getTipoUsuario() {
		return this.TipoUsuario;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.TipoUsuario = tipoUsuario;
	}
	public DatoPersonal getDatoPersonal() {
		return this.DatoPersonal;
	}
	public void setDatoPersonal(DatoPersonal datoPersonal) {
		this.DatoPersonal = datoPersonal;
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
