package com.whereeco.controller;

import com.whereeco.controller.dto.UserJoinDto;
import com.whereeco.domain.user.entity.User;
import com.whereeco.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/list")
    public String list(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("user", users);
        return "user/list";
    }

    @GetMapping("join")
    public String create(Model model) {
        model.addAttribute("userJoinDto", new UserJoinDto());
        return "user/edit1";
    }

    @PostMapping("join")
    public String create(HttpServletResponse response, UserJoinDto userJoinDto, Model model) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        if( !userJoinDto.getPwd1().equals(userJoinDto.getPwd2())){
            out.println("<script> alert('비밀번호 확인 불일치'); </script>");
            out.flush();
            return "user/edit1";
        }

        String securePassword = passwordEncoder.encode(userJoinDto.getPwd1());
        User user = new User(userJoinDto.getUserId(), securePassword, userJoinDto.getName());
        userService.save(user);
        out.println("<script>alert('회원가입 성공, 로그인하세요');</script>");
        out.flush();
        return "redirect:login";
    }

    @PostMapping("/login")
    public void login(HttpServletResponse response, HttpSession session, String userId, String pwd) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        User user = userService.findByUserId(userId);

        if (user != null) {
            if (passwordEncoder.matches(pwd, user.getPwd())) {
                session.setMaxInactiveInterval(3000);
                session.setAttribute("userId", user.getUserId());

                out.println("<script>alert('login 성공'); location.href='/user/map';</script>");
                out.flush();
            } else {
                out.println("<script>alert('아이디 또는 비밀번호가 일치하지 않습니다.'); location.href='/user/login';</script>");
                out.flush();
            }
        } else {
            out.println("<script>alert('아이디 또는 비밀번호가 일치하지 않습니다. 회원가입을 진행해주세요.'); location.href='/user/join';</script>");
            out.flush();
        }
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/map")
    public String map() {
        return "user/map";
    }
}
