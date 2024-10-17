package com.springBoot.springSecurity.service;

import com.springBoot.springSecurity.dao.UserPrincipal;
import com.springBoot.springSecurity.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.springBoot.springSecurity.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user==null){
            System.out.println("user not fount");
            throw new UsernameNotFoundException(("user not found"));
        }
        else{
            return new UserPrincipal(user);
        }
    }
}
