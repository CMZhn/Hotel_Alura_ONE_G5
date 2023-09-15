package com.latam.cmz.hotelalura.dao;

import java.util.List;

import javax.persistence.EntityManager;
import com.latam.cmz.hotelalura.modelo.Nacionalidad;


public class NacionalidadDAO {
	private EntityManager em;

	public NacionalidadDAO(EntityManager em) {
		this.em = em;
	}
	
	public Nacionalidad ConsultaPorId (long id) {
		return em.find(Nacionalidad.class, id);
	}
	
	public List<Nacionalidad> ConsultarTodos (){
		String jpql = "SELECT N FROM Nacionalidad AS N";
		return em.createQuery(jpql, Nacionalidad.class).getResultList();
	}

}
