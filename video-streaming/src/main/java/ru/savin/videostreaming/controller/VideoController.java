package ru.savin.videostreaming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.savin.videostreaming.constant.ControllerConstant;
import ru.savin.videostreaming.entity.Video;
import ru.savin.videostreaming.entity.VideoRequest;
import ru.savin.videostreaming.service.VideoService;

import java.util.List;

@RestController
@RequestMapping(ControllerConstant.ROOT_PATH + "videos")
public class VideoController {
    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Video>> getVideosByUsername(@PathVariable String username) {
        return ResponseEntity.ok(videoService.getVideosByUsername(username));
    }

    @PostMapping()
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                   @RequestParam String username,
                                                   @RequestParam String title) {
        VideoRequest videoRequest = new VideoRequest(title, username);
        videoService.addVideo(file, videoRequest);
        return ResponseEntity.ok("");
    }
}
