package com.spring.springJPA.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.springJPA.enums.IdentityType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Identity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Size(message = "Identification number cannot be less than 8 and greater than 16", min = 8,max = 16)
    private String number;

    @Column(nullable = false)
    private IdentityType identityType;

    @OneToOne(mappedBy = "identity")
    @JsonIgnoreProperties("identity")
    private Student student;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public Identity(String number, IdentityType identityType) {
        this.number = number;
        this.identityType = identityType;
    }

}
