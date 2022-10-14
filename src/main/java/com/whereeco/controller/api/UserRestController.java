package com.whereeco.controller.api;

import com.whereeco.controller.api.dto.UserJoinDto;
import com.whereeco.domain.user.entity.User;
import com.whereeco.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRestController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> users(){
        List<User> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("join")
    public ResponseEntity<String> create(@RequestBody UserJoinDto userJoinDto) {

        if( !userJoinDto.getPwd().equals(userJoinDto.getCheckPwd())){
            return ResponseEntity.ok("Join Failed");
        }

        String securePassword = passwordEncoder.encode(userJoinDto.getPwd());
        User user = new User(userJoinDto.getUserId(), securePassword, userJoinDto.getName());
        userService.save(user);

        return ResponseEntity.ok("join OK");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){

        User foundUser = userService.findByUserId(user.getUserId());

        if (passwordEncoder.matches(user.getPwd(), foundUser.getPwd())) {
            return ResponseEntity.ok("login ok");
        }
        // 비밀번호 불일치
        return ResponseEntity.badRequest().body("login failed");

        // Todo JWT 활용 로그인  유지 기능 추가
    }
}
