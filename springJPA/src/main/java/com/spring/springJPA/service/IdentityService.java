package com.spring.springJPA.service;

import com.spring.springJPA.entity.Identity;
import com.spring.springJPA.jpa.IdentityJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdentityService {

    @Autowired
    IdentityJpaRepository identityJpaRepository;

    public List<Identity> getAll() {
        return identityJpaRepository.findAll();
    }

    public Optional<Identity> getById(int identityId) {
        return identityJpaRepository.findById(identityId);
    }
}
