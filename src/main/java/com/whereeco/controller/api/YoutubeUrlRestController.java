package com.whereeco.controller.api;

import com.whereeco.domain.youtubeurl.entity.YoutubeUrl;
import com.whereeco.domain.youtubeurl.service.YoutubeUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RequiredArgsConstructor
@RestController
public class YoutubeUrlRestController {

    private final YoutubeUrlService youtubeUrlService;
    private final int URL_COUNT = 3;

    @GetMapping("/url/youtube")
    public ResponseEntity<Map<String, String>> getRandomUrl(){
        return ResponseEntity.ok(youtubeUrlService.getRandomUrlMap(URL_COUNT));
    }


}



















