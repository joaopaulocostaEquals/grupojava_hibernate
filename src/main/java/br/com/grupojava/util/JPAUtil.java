package br.com.grupojava.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static final EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("persistencia");

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
