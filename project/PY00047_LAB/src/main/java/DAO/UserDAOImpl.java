package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import LAB1.User;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    private EntityManager em = XJPA.getEntityManager();
    
    @Override
    public User findByIdOrEmail(String identifier) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.id = :identifier OR u.email = :identifier", User.class)
                     .setParameter("identifier", identifier)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null; // Trả về null nếu không tìm thấy
        }
    }

    @Override
    public List<User> findAll() {
        String jpql = "SELECT o FROM User o";  // JPQL để lấy tất cả người dùng
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        return query.getResultList();  // Trả về danh sách người dùng
    }

    @Override
    public User findById(String id) {
        return em.find(User.class, id);  // Truy vấn người dùng theo ID
    }

    @Override
    public void create(User entity) {
        try {
            em.getTransaction().begin();  // Bắt đầu giao dịch
            em.persist(entity);  // Lưu đối tượng vào cơ sở dữ liệu
            em.getTransaction().commit();  // Cam kết giao dịch
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();  // Lùi giao dịch nếu có lỗi
            }
            throw e;  // Ném lại exception sau khi rollback
        }
    }

    @Override
    public void update(User entity) {
        try {
            em.getTransaction().begin();  // Bắt đầu giao dịch
            em.merge(entity);  // Cập nhật hoặc lưu đối tượng vào cơ sở dữ liệu
            em.getTransaction().commit();  // Cam kết giao dịch
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();  // Lùi giao dịch nếu có lỗi
            }
            throw e;  // Ném lại exception sau khi rollback
        }
    }

    @Override
    public void deleteById(String id) {
        User entity = em.find(User.class, id);  // Tìm người dùng theo ID
        if (entity != null) {
            try {
                em.getTransaction().begin();  // Bắt đầu giao dịch
                em.remove(entity);  // Xóa đối tượng khỏi cơ sở dữ liệu
                em.getTransaction().commit();  // Cam kết giao dịch
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();  // Lùi giao dịch nếu có lỗi
                }
                throw e;  // Ném lại exception sau khi rollback
            }
        }
    }

    public void close() {
        if (em.isOpen()) {
            em.close();
        }
    }

	@Override
	public User authenticate(String username, String password) {
		String jpql = "SELECT o FROM User o WHERE o.id = :id AND o.password = :password";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("id", username);
        query.setParameter("password", password);
        
        List<User> users = query.getResultList();
        return users.isEmpty() ? null : users.get(0);
	}
}
