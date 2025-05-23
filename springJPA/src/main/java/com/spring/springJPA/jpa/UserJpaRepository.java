package com.spring.springJPA.jpa;

import com.spring.springJPA.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
}
