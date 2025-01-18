package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class EntityManagerFactory {
	private EntityManager em;
	public static EntityManagerFactory instance = new EntityManagerFactory();

	public static EntityManagerFactory getInstance() {
        return instance;
    }

	public EntityManager getEntityManager() {
		em = Persistence.createEntityManagerFactory("MSSQL").createEntityManager();
        return em;
    }
	public void close() {
		em.close();
	}
}
