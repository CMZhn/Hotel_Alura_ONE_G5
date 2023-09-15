package com.latam.cmz.hotelalura.vo;

import java.time.LocalDate;

public class RelatorioHuesped {
	private Long H_Id;
	private Long Id_DatoPersonal;
	private String Nombre;
	private String Apellido;
	private LocalDate Fecha_de_nacimiento;
	private Long Id_Nacionalidad;
	private String Gentilicio;
	private String Telefono;
	
	
	public RelatorioHuesped(Long h_id,
							Long id_DatoPersonal,
							String nombre,
							String apellido,
							LocalDate fecha_de_nacimiento,
							Long id_Nacionalidad,
							String gentilicio,
							String telefono) {

		H_Id = h_id;
		Id_DatoPersonal = id_DatoPersonal;
		Nombre = nombre;
		Apellido = apellido;
		Fecha_de_nacimiento = fecha_de_nacimiento;
		Id_Nacionalidad = id_Nacionalidad;
		Gentilicio = gentilicio;
		Telefono = telefono;
	}
	//(H.Id, DP.Id, DP.Nombre, DP.Apellido, DP.Fecha_de_nacimiento, N.Id, N.Gentilicio, DP.Telefono)

	public Long getH_Id() {
		return H_Id;
	}


	public void setH_Id(Long h_id) {
		H_Id = h_id;
	}


	public Long getId_DatoPersonal() {
		return Id_DatoPersonal;
	}


	public void setId_DatoPersonal(Long id_DatoPersonal) {
		Id_DatoPersonal = id_DatoPersonal;
	}


	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public String getApellido() {
		return Apellido;
	}


	public void setApellido(String apellido) {
		Apellido = apellido;
	}


	public LocalDate getFecha_de_nacimiento() {
		return Fecha_de_nacimiento;
	}


	public void setFecha_de_nacimiento(LocalDate fecha_de_nacimiento) {
		Fecha_de_nacimiento = fecha_de_nacimiento;
	}


	public Long getId_Nacionalidad() {
		return Id_Nacionalidad;
	}


	public void setId_Nacionalidad(Long id_Nacionalidad) {
		Id_Nacionalidad = id_Nacionalidad;
	}


	public String getGentilicio() {
		return Gentilicio;
	}


	public void setGentilicio(String gentilicio) {
		Gentilicio = gentilicio;
	}


	public String getTelefono() {
		return Telefono;
	}


	public void setTelefono(String telefono) {
		Telefono = telefono;
	}


	@Override
	public String toString() {
		return "RelatorioHuesped [Id=" + H_Id + ", Id_DatoPersonal=" + Id_DatoPersonal + ", Nombre=" + Nombre
				+ ", Apellido=" + Apellido + ", Fecha_de_nacimiento=" + Fecha_de_nacimiento + ", Id_Nacionalidad="
				+ Id_Nacionalidad + ", Gentilicio=" + Gentilicio + ", Telefono=" + Telefono + "]";
	}
	
	
	
	/*
	 * String jpql = "SELECT H.Id,"
				+ " DP.Id,"*******************
				+ " DP.Nombre,"*******************
				+ " DP.Apellido,"*******************
				+ " DP.Fecha_de_nacimiento,"*******************
				+ " N.Gentilicio,"*******************
				+ " DP.Telefono"
				+ " FROM Huesped H"
				+ " JOIN H.DatoPersonal DP"
				+ " JOIN DP.Nacionalidad N"
				+ " WHERE H.Activo=1 "
				+ " GROUP BY H.ID"
				+ " ORDER BY DP.Apellido DESC";
	 */

}
