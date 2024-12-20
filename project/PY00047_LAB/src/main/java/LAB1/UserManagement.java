package LAB1;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class UserManagement {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("PY00047_LAB");
	EntityManager em = factory.createEntityManager();
	// XUẤT RA TẤT CẢ USER
    public void findAll() {
        String jpql = "SELECT o FROM User o";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        List<User> list = query.getResultList();
        list.forEach(user -> {
            String fullName = user.getFullname();
            boolean admin = user.getAdmin();
            System.out.println(fullName + ": " + admin);
        });
    }

    // TÌM DỰA TRÊN KHOÁ CHÍNH
    public void findById(String id) {
        User user = em.find(User.class, id);
        if (user != null) {
            String fullname = user.getFullname();
            System.out.println(fullname);
        } else {
            System.out.println("User with ID " + id + " not found.");
        }
    }

    // TẠO
    public void create(User user) {
        try {
            em.getTransaction().begin();
            
            // Kiểm tra xem người dùng đã tồn tại hay chưa
            User existingUser = em.find(User.class, user.getId());
            if (existingUser != null) {
                System.out.println("User with ID " + user.getId() + " already exists. User creation failed.");
                em.getTransaction().rollback();
                return; // Thoát khỏi phương thức nếu người dùng đã tồn tại
            }
            
            em.persist(user);
            em.getTransaction().commit();
            System.out.println("User created: " + user.getFullname());
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    
    public void findUsersByPage(int pageNumber, int pageSize) {
        try {
            // Tạo JPQL để lấy người dùng
            String jpql = "SELECT o FROM User o";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            
            // Thiết lập vị trí bắt đầu và số lượng tối đa
            query.setFirstResult(pageNumber * pageSize); // pageNumber = 2 cho trang 3
            query.setMaxResults(pageSize);
            
            // Thực hiện truy vấn
            List<User> list = query.getResultList();
            
            // Hiển thị danh sách người dùng
            list.forEach(user -> {
                String fullName = user.getFullname();
                String email = user.getEmail();
                System.out.println("Full name: " + fullName + ", Email: " + email);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // SỬA
    public void update(String id) {
        User user = em.find(User.class, id);
        if (user != null) {
            user.setFullname("Tran Van C");
            try {
                em.getTransaction().begin();
                em.merge(user);
                em.getTransaction().commit();
                System.out.println("User updated: " + user.getFullname());
            } catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("User with ID " + id + " not found. Update failed.");
        }
    }
    public void findUsersByEmailAndRole() {
        String jpql = "SELECT o FROM User o WHERE o.email LIKE :search AND o.admin = :role";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("search", "%@example.com");
        query.setParameter("role", false);

        List<User> list = query.getResultList();
        list.forEach(user -> {
            String fullName = user.getFullname();
            String email = user.getEmail();
            System.out.println("Full name: " + fullName + ", Email: " + email);
        });
    }


    // XOÁ
    public void deleteById(String id) {
        User user = em.find(User.class, id);
        if (user != null) {
            try {
                em.getTransaction().begin();
                em.remove(user);
                em.getTransaction().commit();
                System.out.println("User deleted with ID: " + id);
            } catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("User with ID " + id + " not found. Delete failed.");
        }
    }
}
