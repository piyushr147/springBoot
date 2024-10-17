package com.springBoot.springSecurity.controller;

import com.springBoot.springSecurity.model.User;
import com.springBoot.springSecurity.service.CustomUserDetailsService;
import com.springBoot.springSecurity.service.JwtService;
import com.springBoot.springSecurity.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("users")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("register")
    public String register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("login")
    public String login(@RequestBody User user) {
        System.out.println(user);
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));

        UserDetails user1 = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder().username("navin").password("n@123").roles("USER").build();
        UserDetails user2 = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder().username("admin").password("admin@789").roles("ADMIN").build();

        ProviderManager providerManager = new ProviderManager(daoAuthenticationProvider);
        Authentication authentication = providerManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());
        else
            return "false";
    }
}
