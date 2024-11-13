package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

import LAB3.Video;

public class VideoDAOImpl implements VideoDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("LAB3_PU");

    @Override
    public Video findById(String id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Video.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Video> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Video> query = em.createQuery("SELECT v FROM Video v", Video.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void save(Video video) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(video);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Video video) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(video);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(String id) {
        EntityManager em = emf.createEntityManager();
        try {
            Video video = em.find(Video.class, id);
            if (video != null) {
                em.getTransaction().begin();
                em.remove(video);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
