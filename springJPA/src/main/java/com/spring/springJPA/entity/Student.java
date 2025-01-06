package com.spring.springJPA.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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
    @OneToOne(fetch = FetchType.LAZY)
    private Identity identity;
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
