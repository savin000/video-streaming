package ru.savin.videostreaming.controller;

import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.savin.videostreaming.constant.ControllerConstant;
import org.springframework.beans.factory.annotation.Autowired;
import ru.savin.videostreaming.service.VideoStreamingService;

@RestController
@RequestMapping(ControllerConstant.ROOT_PATH + "video-streaming")
public class VideoStreamingController {
    @Autowired
    private VideoStreamingService service;

    @GetMapping(value = "{title}", produces = "video/mp4")
    public Mono<Resource> getVideos(@PathVariable String title, @RequestHeader("Range") String range, @RequestParam("username") String username) {
        System.out.println("range in bytes() : " + range);
        return service.getVideo(title, username);
    }
}
