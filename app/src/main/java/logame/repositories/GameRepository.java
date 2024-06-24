package logame.repositories;

import java.util.List;

import logame.entities.Game;

public class GameRepository{

    /*@Override
    public List<Game> findAll() {
        EntityManagerFactory emf = PeristenceUnit.getEmf();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Game> games = em.createQuery("SELECT * FROM Game", Game.class).getResultList();
        em.close();
        emf.close();
        return games;
    }

    @Override
    public Game findById(Number id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("logame");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Game g = em.find(Game.class, id);
        em.close();
        emf.close();
        return g;
    }

    @Override
    public void create(Game entity) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("logame");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @Override
    public void update(Game entity) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("logame");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }*/
    
}
