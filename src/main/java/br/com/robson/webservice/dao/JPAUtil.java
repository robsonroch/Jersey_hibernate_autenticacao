package br.com.robson.webservice.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf;

	public static EntityManager getEntityManager(String model) {
		 if (emf == null) {
			 emf = Persistence.createEntityManagerFactory(model);
		 }
		 return emf.createEntityManager();
	}
	
}
