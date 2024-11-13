package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;
import LAB3.Favorite;

public class FavoriteDAOImpl implements FavoriteDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PY00047_LAB");

    @Override
    public Favorite findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Favorite.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Favorite> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Favorite> query = em.createQuery("SELECT f FROM Favorite f", Favorite.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Favorite> findByUserId(String userId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Favorite> query = em.createQuery("SELECT f FROM Favorite f WHERE f.user.id = :userId", Favorite.class);
            query.setParameter("userId", userId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void save(Favorite favorite) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(favorite);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Favorite favorite = em.find(Favorite.class, id);
            if (favorite != null) {
                em.getTransaction().begin();
                em.remove(favorite);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

    @Override
    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
