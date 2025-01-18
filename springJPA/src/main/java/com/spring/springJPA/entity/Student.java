package com.spring.springJPA.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries(value = {
        @NamedQuery(name="get_all_Students",query = "select p from Student p"),
        @NamedQuery(name="get_Student_by_name",query = "select p from Student p where p.name = :name")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    private String location;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("student")
    private Identity identity;
    @Setter(AccessLevel.NONE)
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinTable(name = "STUDENT_COURSE",joinColumns = @JoinColumn(name = "STUDENT_ID"),inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    private List<Course> courseList = new ArrayList<>();
    private LocalDateTime birthDate;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime creationDate;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedDate;

    public Student(String name, String location, LocalDateTime birthDate) {
        this.name = name;
        this.location = location;
        this.birthDate = birthDate;
    }

    public void addCourse(Course course){
        courseList.add(course);
    }

    public void removeCourse(Course course){
        courseList.remove(course);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
