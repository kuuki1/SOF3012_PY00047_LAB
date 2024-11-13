package DAO;

import java.util.List;
import LAB3.Favorite;

public interface FavoriteDAO {
    Favorite findById(Long id);
    List<Favorite> findAll();
    List<Favorite> findByUserId(String userId);
    void save(Favorite favorite);
    void delete(Long id);
    void close();
}
