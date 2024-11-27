package LAB5;

import java.util.List;

import DAO.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class LogDAOImpl implements LogDAO {

    public void create(Log log) {
        EntityManager entityManager = XJPA.getEntityManager(); // Lấy EntityManager từ XJPA

        try {
            entityManager.getTransaction().begin(); // Bắt đầu giao dịch
            entityManager.persist(log); // Lưu bản ghi vào CSDL
            entityManager.getTransaction().commit(); // Xác nhận giao dịch
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Rollback nếu có lỗi
            }
            e.printStackTrace();
        } finally {
            entityManager.close(); // Đảm bảo đóng EntityManager sau khi xong
        }
        
    }
    public void checkLogs() {
        EntityManager entityManager = XJPA.getEntityManager();
        
        try {
            // Truy vấn tất cả các bản ghi trong bảng Logs
            Query query = entityManager.createQuery("SELECT l FROM Log l");
            List<Log> logs = query.getResultList();
            
            // In kết quả ra console để kiểm tra
            for (Log log : logs) {
                System.out.println("URL: " + log.getUrl());
                System.out.println("Access Time: " + log.getAccessTime());
                System.out.println("Fullname: " + log.getFullname());
                System.out.println("-------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
}}