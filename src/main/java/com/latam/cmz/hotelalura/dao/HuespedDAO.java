package com.latam.cmz.hotelalura.dao;

import java.util.List;

import javax.persistence.EntityManager;
import com.latam.cmz.hotelalura.modelo.Huesped;
import com.latam.cmz.hotelalura.vo.RelatorioHuesped;


public class HuespedDAO {
	private EntityManager em;

	public HuespedDAO(EntityManager em) {
		this.em = em;
	}
	
	public void guardar (Huesped huesped) {
		this.em.persist(huesped);
	}
	
	public void actualizar(Huesped huesped) {
		this.em.merge(huesped);
	}
	
	public Huesped ConsultaPorId (long id) {
		return em.find(Huesped.class, id);
	}
	
	public Huesped ConsultaPorIdCompleto (long id) {
		String jpql="SELECT h FROM Huesped h JOIN FETCH h.DatoPersonal dp"
				+ " JOIN FETCH dp.Nacionalidad WHERE h.Id=:id";
		return em.createQuery(jpql,Huesped.class).setParameter("id", id).getSingleResult();
	}
	
	public List<Huesped> ConsultarTodos (){
		String jpql = "SELECT H FROM Huesped AS H WHERE H.Activo=1";
		return em.createQuery(jpql, Huesped.class).getResultList();
	}
	public List<Huesped> ConsultarTodosOrdenporApellido (){
		String jpql = "SELECT H FROM Huesped H"
				+ " JOIN FETCH H.DatoPersonal DP"
				+ " WHERE H.Activo=1"
				+ " GROUP BY H.Id"
				+ " ORDER BY DP.Apellido";
		return em.createQuery(jpql, Huesped.class).getResultList();
	}
	
	public List<RelatorioHuesped> RelatorioHuespedesVO() {
		String jpql = "SELECT new com.latam.cmz.hotelalura.vo.RelatorioHuesped (H.Id,"
				+ " DP.Id,"
				+ " DP.Nombre,"
				+ " DP.Apellido,"
				+ " DP.Fecha_de_nacimiento,"
				+ " N.Id,"
				+ " N.Gentilicio,"
				+ " DP.Telefono)"
				+ " FROM Huesped H"
				+ " JOIN H.DatoPersonal DP"
				+ " JOIN DP.Nacionalidad N"
				+ " WHERE H.Activo=1 "
				+ " GROUP BY H.Id"
				+ " ORDER BY DP.Apellido";
		return em.createQuery(jpql,RelatorioHuesped.class).getResultList();
		
	}

	
	public List<Object[]> RelatorioHuespedes() {
		String jpql = "SELECT H.Id,"
				+ " DP.Id,"
				+ " DP.Nombre,"
				+ " DP.Apellido,"
				+ " DP.Fecha_de_nacimiento,"
				+ " N.Id,"
				+ " N.Gentilicio,"
				+ " DP.Telefono"
				+ " FROM Huesped H"
				+ " JOIN H.DatoPersonal DP"
				+ " JOIN DP.Nacionalidad N"
				+ " WHERE H.Activo=1 "
				+ " GROUP BY H.Id"
				+ " ORDER BY DP.Apellido";
		return em.createQuery(jpql,Object[].class).getResultList();
		
	}


}
