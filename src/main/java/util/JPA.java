package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPA {

    private static final  EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Loja");
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

    public static EntityManager getEntityManager(){
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }
}
