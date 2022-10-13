package com.whereeco.controller;

import com.whereeco.domain.user.service.UserService;
import com.whereeco.domain.youtubeurl.entity.YoutubeUrl;
import com.whereeco.domain.youtubeurl.service.YoutubeUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final YoutubeUrlService youtubeUrlService;

    @GetMapping("/")
    public void test1() {
        //userService.refreshAll();
        //userService.deleteById(1L);
    }

}

























