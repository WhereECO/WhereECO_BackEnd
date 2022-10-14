package com.whereeco.controller.api;

import com.whereeco.controller.api.dto.TodoDto;
import com.whereeco.controller.api.dto.UserJoinDto;
import com.whereeco.controller.api.login.dto.TokenDto;
import com.whereeco.controller.api.login.service.LoginService;
import com.whereeco.domain.jwt.constant.GrantType;
import com.whereeco.domain.jwt.constant.TokenType;
import com.whereeco.domain.jwt.service.TokenProvider;
import com.whereeco.domain.user.entity.User;
import com.whereeco.domain.user.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

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

        return ResponseEntity.ok("join OK");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto.Response> login(@RequestBody TokenDto.Request request){

        TokenDto.Response response = loginService.login(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/todo")
    public ResponseEntity<TodoDto> getTodo(HttpServletRequest request){
        //  1. authorization 헤더가 있는지 체크
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.hasText(authorizationHeader)){
            throw new RuntimeException("authorization 헤더 아님");
        }

        //  2. authorization Header의 TokenType Bearer 체크
        String[] authorizations = authorizationHeader.split(" ");
        if( authorizations.length < 2 || ( !GrantType.BEARER.getType().equals(authorizations[0]))){
            throw new RuntimeException("BEARER 토큰 아님");
        }

        //  3. 토큰 검증
        String token = authorizations[1];   // token 변수는 액세스 토큰의 몸통 부분.
        if( !tokenProvider.validateToken(token)){
            throw new RuntimeException("토큰 값 오류");
        }

        //  4. 토큰 타입 검증. ACCESS or REFRESH
        String tokenType = tokenProvider.getTokenType(token);
        if( !TokenType.ACCESS.name().equals(tokenType)){
            throw new RuntimeException("토큰타입 ACCESS 아님");
        }

        //  5. 액세스 토큰 만료 시작 검증
        // 일단 isTokenExpired() 인자로 넣을 토큰만료일 Date 객체를 가져옴
        Claims tokenClaims = tokenProvider.getTokenClaims(token);
        Date expiration = tokenClaims.getExpiration();

        if (tokenProvider.isTokenExpired(expiration)) {
            throw new RuntimeException("토큰 만료됨");
        }

        System.out.println(tokenClaims.getAudience());

        TodoDto todo = userService.findTodo(tokenClaims.getAudience());

        return ResponseEntity.ok(todo);
    }
}
