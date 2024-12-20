package DAO;

import LAB1.User;
import java.util.List;

public interface UserDAO {

    List<User> findAll();

    User findById(String id);
    
    User findByIdOrEmail(String identifier);

    void create(User item);

    void update(User item);

    void deleteById(String id);

	void close();

	User authenticate(String username, String password);
}
