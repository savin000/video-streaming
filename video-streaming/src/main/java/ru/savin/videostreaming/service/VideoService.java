package ru.savin.videostreaming.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.savin.videostreaming.entity.Video;
import ru.savin.videostreaming.entity.VideoRequest;
import ru.savin.videostreaming.repository.VideoRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class VideoService {
    private static final String NO_VIDEO_FOUND = "No video found";
    private static final String VIDEO_ALREADY_EXISTS = "Video already exists";

    private final VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public String addVideo(MultipartFile file, VideoRequest videoRequest) {
        if (this.videoRepository.findVideoByTitleAndUsername(videoRequest.getTitle(), videoRequest.getUsername()).isPresent()) {
            throw new EntityExistsException(VIDEO_ALREADY_EXISTS);
        } else {
            String pathAsString = "src/main/resources/video/" + file.getOriginalFilename();
            Video video = new Video(videoRequest.getUsername(), videoRequest.getTitle(), file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf('.')));
            try {
                Path path = Paths.get(pathAsString);
                Files.write(path, file.getBytes());
                this.videoRepository.save(video);
            } catch (Exception e) {

            }
        }

        return "ok";
    }

    public List<Video> getVideosByUsername(String username) {
        return this.videoRepository.getAllByUsername(username);
    }

    public Video getVideoByTitleAndUsername(String title, String username) {
        return this.videoRepository.findVideoByTitleAndUsername(title, username)
                .orElseThrow(() -> new EntityNotFoundException(NO_VIDEO_FOUND));
    }
}
