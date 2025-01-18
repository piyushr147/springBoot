package com.spring.springJPA.jpa;

import com.spring.springJPA.entity.Identity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityJpaRepository extends JpaRepository<Identity,Integer> {
}
