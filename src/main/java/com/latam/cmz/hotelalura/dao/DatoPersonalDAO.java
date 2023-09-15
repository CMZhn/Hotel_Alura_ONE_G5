package com.latam.cmz.hotelalura.dao;

import java.util.List;
import javax.persistence.EntityManager;
import com.latam.cmz.hotelalura.modelo.DatoPersonal;

public class DatoPersonalDAO {
	private EntityManager em;

	public DatoPersonalDAO(EntityManager em) {
		this.em = em;
	}
	
	public void guardar (DatoPersonal datoPersonal) {
		this.em.persist(datoPersonal);
	}
	public DatoPersonal ConsultaPorId (long id) {
		return em.find(DatoPersonal.class, id);
	}
	
	public List<DatoPersonal> ConsultarTodos (){
		String jpql = "SELECT D FROM DatoPersonal AS D";
		return em.createQuery(jpql, DatoPersonal.class).getResultList();
	}
	
	
	public DatoPersonal ConsultaPorIdCompleta (Long id){
		String jpql = "SELECT D FROM DatoPersonal AS D JOIN FETCH D.Nacionalidad WHERE D.id=:id";
		return em.createQuery(jpql, DatoPersonal.class).setParameter("id", id).getSingleResult();
	}
	

}
