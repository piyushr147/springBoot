package com.spring.springJPA.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String courseName;
    @Column(nullable = false)
    private int courseDuration;
    @OneToMany(mappedBy = "course")
    @Setter(AccessLevel.NONE)
    private List<Review> reviewList = new ArrayList<>();
    @CreationTimestamp
    private LocalDateTime creationDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    public void addReview(Review review){
        reviewList.add(review);
    }

    public void removeReview(Review review){
        reviewList.remove(review);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", courseDuration=" + courseDuration +
                ", createDateTime=" + creationDate +
                ", updateDateTime=" + updatedDate +
                '}';
    }
}
