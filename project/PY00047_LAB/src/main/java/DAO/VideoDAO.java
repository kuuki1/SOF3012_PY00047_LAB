package DAO;

import java.util.List;

import LAB3.Video;

public interface VideoDAO {
    Video findById(String id);
    List<Video> findAll();
    void save(Video video);
    void update(Video video);
    void delete(String id);
}
