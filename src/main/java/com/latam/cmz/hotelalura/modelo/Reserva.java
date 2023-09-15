package com.latam.cmz.hotelalura.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Table(name="Reservas")
public class Reserva {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "BIGINT UNSIGNED")
	private Long Id;
	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name = "Id_Usuario", nullable = false)
	private Usuario Usuario;
	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name = "Id_Habitacion", nullable = false)
	private Habitacion Habitacion;
	@Column(nullable = false)
	private LocalDate Fecha_entrada;
	@Column(nullable = false)
	private LocalDate Fecha_salida;
	private BigDecimal Valor_Total;
	@Column(length = 25, nullable = false)
	private String Forma_de_pago;
	@ManyToMany
	@JoinTable (
	        name = "Reserva_Huesped", 
	        joinColumns = { @JoinColumn(name = "Id_Reserva") }, 
	        inverseJoinColumns = { @JoinColumn(name = "Id_Huesped") })
	private List<Huesped> Huespedes=new ArrayList<>();
	
	
	
	

	public Reserva() {}
	
	public Reserva(Usuario usuario,
					Habitacion habitacion,
					LocalDate fecha_entrada,
					LocalDate fecha_salida,
					BigDecimal valor_total,
					String forma_de_pago) {
		
		this.Usuario=usuario;
		this.Habitacion=habitacion;
		this.Fecha_entrada=fecha_entrada;
		this.Fecha_salida=fecha_salida;
		this.Valor_Total=valor_total;
		this.Forma_de_pago=forma_de_pago;
	}
	
	public Reserva(Usuario usuario,
			Habitacion habitacion,
			LocalDate fecha_entrada,
			LocalDate fecha_salida,
			BigDecimal valor_total,
			String forma_de_pago,
			List<Huesped> huespedes) {

		this.Usuario=usuario;
		this.Habitacion=habitacion;
		this.Fecha_entrada=fecha_entrada;
		this.Fecha_salida=fecha_salida;
		this.Valor_Total=valor_total;
		this.Forma_de_pago=forma_de_pago;
		this.Huespedes=huespedes;
	}


	public Usuario getUsuario() {
		return this.Usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.Usuario = usuario;
	}
	
	public Habitacion getHabitacion() {
		return this.Habitacion;
	}
	public void setHabitacion(Habitacion habitacion) {
		this.Habitacion = habitacion;
	}
	public LocalDate getFecha_entrada() {
		return this.Fecha_entrada;
	}
	public void setFecha_entrada(LocalDate fecha_entrada) {
		this.Fecha_entrada = fecha_entrada;
	}
	public LocalDate getFecha_salida() {
		return this.Fecha_salida;
	}
	public void setFecha_salida(LocalDate fecha_salida) {
		this.Fecha_salida = fecha_salida;
	}
	public BigDecimal getValor_Total() {
		return this.Valor_Total;
	}
	public void setValor_Total(BigDecimal valor_Total) {
		this.Valor_Total = valor_Total;
	}
	public String getForma_de_pago() {
		return this.Forma_de_pago;
	}
	public void setForma_de_pago(String forma_de_pago) {
		this.Forma_de_pago = forma_de_pago;
	}
	public Long getId() {
		return Id;
	}
	
	public List<Huesped> getHuespedes() {
		return this.Huespedes;
	}

	public void setHuespedes(List<Huesped> huespedes) {
		this.Huespedes = huespedes;
	}
	
	public void AddHuesped(Huesped huesped) {
		this.Huespedes.add(huesped);
	}	
	
	public void RemuveHuesped(int index) {
		if (index<Huespedes.size()) {
			this.Huespedes.remove(index);
		}
	}
	
}
