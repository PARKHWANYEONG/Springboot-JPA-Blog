package com.welcome.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class UserController {
    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }
    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }
    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }
}
