package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class XJPA {
    private static EntityManagerFactory factory;

    // Tạo EntityManagerFactory một lần khi ứng dụng khởi động
    static {
        factory = Persistence.createEntityManagerFactory("PY00047_LAB");
    }

    // Phương thức trả về EntityManager để thực hiện các thao tác với CSDL
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}

