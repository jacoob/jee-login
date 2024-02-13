package mahdi.learning.jee.loginwebprofile.entity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;


public class MyEntityManager {
    private static EntityManager entityManager = Persistence.createEntityManagerFactory("default").createEntityManager();
//    @PersistenceContext(unitName = "loginUnit")
//    private EntityManager entityManager;
////
//    @Produces
//    public EntityManager getEntityManager(){
//        return entityManager;
//    }

        public static EntityManager getEntityManager(){
        return entityManager;
    }

    private void t(){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
            EntityManager entityManager1 = entityManagerFactory.createEntityManager();
            entityManager1.getTransaction().begin();
            entityManager1.persist(new Profile());
            entityManager1.getTransaction().commit();
            entityManager1.close();
            entityManagerFactory.close();

    }
}
