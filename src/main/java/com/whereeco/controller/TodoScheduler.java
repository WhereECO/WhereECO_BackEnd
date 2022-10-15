package com.whereeco.controller;

import com.whereeco.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class TodoScheduler {

    private final UserService userService;

    //@Scheduled(fixedDelay = 5000)	// 5초마다
    @Scheduled(cron = "0 0 0 * * ?")    // 매일 00시 정각
    public void todoTask() {
        userService.refreshAll();
        System.out.println("log: test");
    }
}
