package com.spring.springJPA.controller;

import com.spring.springJPA.entity.User;
import com.spring.springJPA.service.JwtService;
import com.spring.springJPA.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid User user){
        try {
            User registeredUser = userService.register(user);
            return new ResponseEntity<>(registeredUser,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid User user){
       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
       if(authentication.isAuthenticated()){
           String jwtToken = jwtService.generateToken(user.getUsername());
           return new ResponseEntity<>(jwtToken,HttpStatus.OK);
       }
       else{
            return new ResponseEntity<>("Error in creating Jwt token",HttpStatus.BAD_REQUEST);
       }
    }
}
