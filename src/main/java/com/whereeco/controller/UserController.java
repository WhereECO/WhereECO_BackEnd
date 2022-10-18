package com.whereeco.controller;

import com.whereeco.controller.dto.LoginDto;
import com.whereeco.controller.dto.TodoDto;
import com.whereeco.controller.dto.UserJoinDto;
import com.whereeco.domain.user.entity.User;
import com.whereeco.domain.user.service.UserService;
import com.whereeco.domain.youtubeurl.entity.YoutubeUrl;
import com.whereeco.domain.youtubeurl.service.YoutubeUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final YoutubeUrlService youtubeUrlService;

    private final String URL_PREFIX = "https://www.youtube.com/embed/";

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
    public String create(@Valid UserJoinDto userJoinDto,
                       BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            return "user/edit1";
        }

        if( !userJoinDto.getPwd1().equals(userJoinDto.getPwd2())){
            model.addAttribute("pwdNoMatch", true);
            return "user/edit1";
        }

        String securePassword = passwordEncoder.encode(userJoinDto.getPwd1());
        User user = new User(userJoinDto.getUserId(), securePassword, userJoinDto.getName());
        userService.save(user);
        return "redirect:user/edit1";
    }

    @PostMapping("/login")
    public String  login(HttpSession session,
                      @Valid LoginDto loginDto, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            return "user/login";
        }
        User user= userService.findByUserId(loginDto.getUserId());

        if (user != null) {
            if (passwordEncoder.matches(loginDto.getPwd(), user.getPwd())) {
                session.setMaxInactiveInterval(3000);
                session.setAttribute("userId", user.getUserId());
                return "redirect:map";
            }
            model.addAttribute("noMatchUserIdAndPwd", true);
            return "user/login";
        }
        model.addAttribute("noMatchUserIdAndPwd", true);
        return "user/login";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/map")
    public String map(Model model, HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute("userId");
        User user = userService.findByUserId(userId);
        model.addAttribute("user", user); // TodoList 1, 2, 3

        Map<String, String> randomUrl = youtubeUrlService.getRandomUrlMap(YoutubeUrl.URL_COUNT, URL_PREFIX);
        model.addAttribute("randomUrl", randomUrl);

        return "user/map";
    }


    @PostMapping ("/map")
    public void map(TodoDto todoDto, HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 세션 정보와 일치하는 유저를 가져옴
        String userId = (String) request.getSession().getAttribute("userId");



        // 유저를 Persistence Context에 올림
        userService.updateTodoByUserId(userId, todoDto);

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();


        out.println("<script>alert('저장 완료'); location.href='/user/map';</script>");
        out.flush();
        // return "redirect:map"; // sendRedirect() 보낼 수 없음
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:login";
    }
}

