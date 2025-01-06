package com.spring.springJPA.entity;

import jakarta.persistence.*;
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
    private String number;
    @Column(nullable = false)
    private String identityType;
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "identity")
    private Student student;
    @CreationTimestamp
    private LocalDateTime createDateTime;
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public Identity(String number, String identityType) {
        this.number = number;
        this.identityType = identityType;
    }

}
