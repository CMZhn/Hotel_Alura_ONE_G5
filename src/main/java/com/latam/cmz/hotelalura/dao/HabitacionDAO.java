package com.latam.cmz.hotelalura.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.latam.cmz.hotelalura.modelo.Habitacion;


public class HabitacionDAO {
	private EntityManager em;

	public HabitacionDAO(EntityManager em) {
		this.em = em;
	}
	
	public Habitacion ConsultaPorId (long id) {
		return em.find(Habitacion.class, id);
	}
	
	public List<Habitacion> ConsultarTodos (){
		String jpql = "SELECT H FROM Habitacion AS H WHERE H.Activo=1";
		return em.createQuery(jpql, Habitacion.class).getResultList();
	}
	
	public List<Habitacion> ConsultarDisponibles (LocalDate FechaEntrada,LocalDate FechaSalida){

		String jpql = "SELECT H FROM Habitacion AS H LEFT JOIN Reserva AS R ON H.Id=R.Habitacion"
					+ " WHERE"
					+ "	H.Activo=:a AND ("
					+ "	(R.Fecha_entrada<=:b AND R.Fecha_salida>=:c)"
					+ " OR (R.Fecha_entrada>:b AND R.Fecha_entrada<:c)"
					+ " OR (R.Fecha_salida>:b AND R.Fecha_salida<:c))"
					+ " GROUP BY H.Id";
		TypedQuery<Habitacion> query = em.createQuery(jpql, Habitacion.class);
		
		query.setParameter("a", true);
		query.setParameter("b", FechaEntrada);
		query.setParameter("c", FechaSalida);
		
		return query.getResultList();	
	}

}
