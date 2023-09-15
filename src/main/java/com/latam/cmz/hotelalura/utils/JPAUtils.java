package com.latam.cmz.hotelalura.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {
	private static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("hotel-alura");
	
	public static EntityManager getEntityManager () {
		return FACTORY.createEntityManager();
	}
}
