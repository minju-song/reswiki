package com.teddybear.reswiki;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // 메인 홈
    @GetMapping("/")
    public String home() {return "home/index.html";}

    // 로그인
    @GetMapping("/loginForm")
    public String login() {return "login/login.html";}

    // 회원가입
    @GetMapping("/signupForm")
    public String signup() {return "login/signup.html";}
}
