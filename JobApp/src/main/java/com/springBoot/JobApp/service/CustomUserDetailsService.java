package com.springBoot.JobApp.service;

import com.springBoot.JobApp.dao.UserPrincipal;
import com.springBoot.JobApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.springBoot.JobApp.model.User;

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
