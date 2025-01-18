package com.spring.springJPA.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String courseName;
    @Column(nullable = false)
    private int courseDuration;
    @Setter(AccessLevel.NONE)
    @JsonManagedReference
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Review> reviews = new ArrayList<>();
    @Setter(AccessLevel.NONE)
    @JsonManagedReference
    @ManyToMany(fetch =  FetchType.EAGER, mappedBy = "courseList",cascade = {CascadeType.ALL})
    private List<Student> students = new ArrayList<>();
    @CreationTimestamp
    private LocalDateTime creationDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    public Course(String courseName,int courseDuration){
        this.courseDuration = courseDuration;
        this.courseName = courseName;
    }
    public void addReview(Review review){
        reviews.add(review);
    }

    public void removeReview(Review review){
        reviews.remove(review);
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public void removeStudent(Student student){
        students.remove(student);
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
