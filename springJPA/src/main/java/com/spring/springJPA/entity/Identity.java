package com.spring.springJPA.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.springJPA.enums.IdentityType;
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
    private IdentityType identityType;
    @OneToOne(fetch = FetchType.EAGER,mappedBy = "identity")
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
