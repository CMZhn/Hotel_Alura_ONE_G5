package com.latam.cmz.hotelalura.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.latam.cmz.hotelalura.modelo.Reserva;


public class ReservaDAO {
	private EntityManager em;

	public ReservaDAO(EntityManager em) {
		this.em = em;
	}
	
	public void guardar (Reserva reserva) {
		this.em.persist(reserva);
	}
	public void actualizar(Reserva reserva) {
		this.em.merge(reserva);
	}
	
	public void remover(Reserva reserva) {
		Reserva r= this.em.merge(reserva);
		this.em.remove(r);	
	}
	
	public Reserva ConsultaPorId (long id) {
		return em.find(Reserva.class, id);
	}
	
	public Reserva ConsultaPorIdCompleto (long id) {
		String jpql="SELECT r FROM Reserva r JOIN FETCH r.Habitacion h"
				+ " JOIN FETCH r.Huespedes WHERE r.Id=:id";
		return em.createQuery(jpql,Reserva.class).setParameter("id", id).getSingleResult();
	}
	
	public List<Reserva> ConsultarTodos (){
		String jpql = "SELECT R FROM Reserva AS R";
		return em.createQuery(jpql, Reserva.class).getResultList();
	}
	
	public List<Reserva> ConsultarTodosCompleto (){
		String jpql = "SELECT R FROM Reserva R JOIN FETCH R.Habitacion";
		return em.createQuery(jpql, Reserva.class).getResultList();
	}
	
	public List<Reserva> ConsultarDisposicion (Long Id_Habitacion, Long Id_Reserva, LocalDate FechaEntrada,LocalDate FechaSalida){

		String jpql = "SELECT R FROM Reserva R JOIN FETCH R.Habitacion H"
					+ " WHERE"
					+ "	H.Id=:a AND (NOT R.Id=:b)  AND ("
					+ "	(R.Fecha_entrada<=:c AND R.Fecha_salida>=:d)"
					+ " OR (R.Fecha_entrada>=:c AND R.Fecha_entrada<=:d)"
					+ " OR (R.Fecha_salida>=:c AND R.Fecha_salida<=:d))"
					+ " GROUP BY R.Id ORDER BY R.Fecha_entrada";
		TypedQuery<Reserva> query = em.createQuery(jpql, Reserva.class);
		
		query.setParameter("a", Id_Habitacion);
		query.setParameter("b", Id_Reserva);
		query.setParameter("c", FechaEntrada);
		query.setParameter("d", FechaSalida);
		
		return query.getResultList();	
	}
	
	

}
