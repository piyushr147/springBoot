package com.spring.springJPA.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = {"course"})
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Min(value = 1,message = "Rating cannot be less than 1.")
    @Max(value = 5, message = "Rating cannot be more than 5.")
    private int rating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    @JsonBackReference
    private Course course;

    @NotBlank(message = "Review description cannot be empty")
    @Size(message = "Review description cannot be less than 4 and more than 30 characters", min = 4,max = 30)
    private String description;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public Review(int rating,String description){
        this.rating = rating;
        this.description = description;
    }
}
