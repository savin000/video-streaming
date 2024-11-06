package ru.savin.videostreaming.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.savin.videostreaming.entity.Video;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {
    List<Video> getAllByUsername(String username);

    Optional<Video> findVideoByTitleAndUsername(String title, String username);
}
