package ru.savin.videostreaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.savin.videostreaming.entity.Video;

@Service
public class VideoStreamingService {
    private static final String FORMAT="classpath:video/%s.mp4";

    private ResourceLoader resourceLoader;
    private VideoService videoService;

    @Autowired
    public VideoStreamingService(ResourceLoader resourceLoader, VideoService videoService) {
        this.resourceLoader = resourceLoader;
        this.videoService = videoService;
    }

    public Mono<Resource> getVideo(String title, String username) {
        Video video = this.videoService.getVideoByTitleAndUsername(title, username);
        return Mono.fromSupplier(() -> resourceLoader.
                getResource(String.format(FORMAT, video.getPath())))   ;
    }
}
