package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

import LAB3.Video;
import dto.VideoShareInfoDTO;

public class VideoDAOImpl implements VideoDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PY00047_LAB");
    
    @Override
    public List<Video> findByTitle(String keyword) {
    	EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT v FROM Video v WHERE v.title LIKE :keyword", Video.class)
                 .setParameter("keyword", "%" + keyword + "%")
                 .getResultList();
    }
    
    @Override
    public List<VideoShareInfoDTO> getVideoShareSummary() {
    	EntityManager em = emf.createEntityManager();
        return em.createQuery(
                "SELECT new dto.VideoShareInfoDTO(v.title, COUNT(f.id), MIN(f.likeDate), MAX(f.likeDate)) " +
                "FROM Video v JOIN Favorite f ON f.video = v " +
                "GROUP BY v.id, v.title", // Thêm v.title vào GROUP BY
                VideoShareInfoDTO.class)
                .getResultList();
    }
    
    @Override
    public List<Video> findVideosSharedIn2024() {
    	EntityManager em = emf.createEntityManager();
        return em.createQuery(
                "SELECT v FROM Video v JOIN Favorite f ON f.videoId = v.id " +
                "WHERE FUNCTION('YEAR', f.likeDate) = 2024 " +
                "ORDER BY f.likeDate ASC", Video.class)
                .getResultList();
    }
    
    @Override
    public List<Video> findUnlikedVideos() {
    	EntityManager em = emf.createEntityManager();
        return em.createQuery(
                "SELECT v FROM Video v WHERE v.id NOT IN (SELECT DISTINCT f.videoId FROM Favorite f)", Video.class)
                .getResultList();
    }
    
    @Override
    public List<Video> findTopLikedVideos() {
    	EntityManager em = emf.createEntityManager();
        return em.createQuery(
                "SELECT v FROM Video v JOIN Favorite f ON f.videoId = v.id " +
                "GROUP BY v.id ORDER BY COUNT(f.id) DESC", Video.class)
                .setMaxResults(10)
                .getResultList();
    }


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
