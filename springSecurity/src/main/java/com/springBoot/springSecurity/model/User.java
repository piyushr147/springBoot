package com.springBoot.springSecurity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    private int id;
    private String username;
    private String password;
    private String roles;
}
