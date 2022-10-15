package com.WhereECO.controller.api;

import com.WhereECO.dto.User;
import com.WhereECO.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired PasswordEncoder encoder;
    @Autowired UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<User>> users(){
        List<User> users = userMapper.findAll();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("join")
    public ResponseEntity<String> create(@RequestBody User user) {

        String securePassword = encoder.encode(user.getPwd());
        user.setPwd(securePassword);
        userMapper.insert(user);
        return ResponseEntity.ok("join OK");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){

        User findUser = userMapper.findByUserid(user.getUserid());

        if (encoder.matches(user.getPwd(), findUser.getPwd())) {
            return ResponseEntity.ok("login ok");
        }
        // 비밀번호 불일치
        return ResponseEntity.badRequest().body("login failed");

        // Todo JWT 활용 로그인  유지 기능 추가
    }






}
