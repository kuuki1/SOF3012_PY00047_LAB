package DAO;

import java.util.List;

import LAB3.Video;
import dto.VideoShareInfoDTO;

public interface VideoDAO {
    Video findById(String id);
    List<Video> findAll();
    List<Video> findByTitle(String keyword);
    List<Video> findTopLikedVideos();
    List<VideoShareInfoDTO> getVideoShareSummary();
    List<Video> findVideosSharedIn2024();
    List<Video> findUnlikedVideos();
    void save(Video video);
    void update(Video video);
    void delete(String id);
}
