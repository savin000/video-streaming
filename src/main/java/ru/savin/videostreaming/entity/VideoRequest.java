package ru.savin.videostreaming.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class VideoRequest {
    String title;
    String username;
}
