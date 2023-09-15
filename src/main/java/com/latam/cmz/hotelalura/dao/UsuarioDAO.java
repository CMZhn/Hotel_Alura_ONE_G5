package com.latam.cmz.hotelalura.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.latam.cmz.hotelalura.modelo.Usuario;

public class UsuarioDAO {
	private EntityManager em;

	public UsuarioDAO(EntityManager em) {
		this.em = em;
	}
	
	public Usuario ConsultaPorId (long id) {
		return em.find(Usuario.class, id);
	}
	
	public List<Usuario> ConsultarTodos (){
		String jpql = "SELECT U FROM Usuario AS U JOIN FETCH U.DatoPersonal JOIN FETCH U.TipoUsuario WHERE U.Activo=1";
		return em.createQuery(jpql, Usuario.class).getResultList();
	}

}
