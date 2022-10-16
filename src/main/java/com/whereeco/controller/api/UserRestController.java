package com.whereeco.controller.api;

import com.whereeco.controller.api.dto.TodoCheckAndUrlDto;
import com.whereeco.controller.api.dto.UserJoinDto;
import com.whereeco.controller.api.login.dto.TokenDto;
import com.whereeco.controller.api.login.service.LoginService;
import com.whereeco.domain.jwt.service.TokenProvider;
import com.whereeco.domain.user.entity.User;
import com.whereeco.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRestController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final LoginService loginService;
    private final TokenProvider tokenProvider;

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

        return ResponseEntity.status(HttpStatus.CREATED).body("join OK");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto.Response> login(@RequestBody TokenDto.Request request){

        TokenDto.Response response = loginService.login(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/todo")
    public ResponseEntity<TodoCheckAndUrlDto> getTodo(HttpServletRequest request){

        //  요청 헤더에서 토큰부분만을 추출 (BEARER header,payload,signature
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).split(" ")[1];

        // userId 추출
        String userId = tokenProvider.getTokenClaims(token).getAudience();

        // 반환값 준비
        TodoCheckAndUrlDto todo = userService.findTodoAndUrl(userId);

        return ResponseEntity.ok(todo);
    }

    @Transactional
    @PostMapping ("/todo")
    public ResponseEntity<Map<String, String>> map(TodoCheckAndUrlDto todoCheckAndUrlDto, HttpServletRequest request){

        //  요청 헤더에서 토큰부분만을 추출 (BEARER header,payload,signature
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).split(" ")[1];

        // userId 추출
        String userId = tokenProvider.getTokenClaims(token).getAudience();

        // 유저를 Persistence Context에 올림
        userService.updateTodoCheckByUserId(userId, todoCheckAndUrlDto);

        // 반환값 준비
        HashMap<String, String> returnMap = new HashMap<>();
        returnMap.put("status", "OK");
        return ResponseEntity.ok(returnMap);
    }
}
