package com.springBoot.JobApp.controller;

import com.springBoot.JobApp.model.User;
import com.springBoot.JobApp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("users")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("user")
    public String addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
