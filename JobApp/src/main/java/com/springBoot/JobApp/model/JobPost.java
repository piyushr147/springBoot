package com.springBoot.JobApp.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
public class JobPost {
    @Id
    private int postId;
    private String postProfile;
    private String postDesc;
    private Integer reqExperience;
    private List<String> postTechStack;

    public int getPostId() {
        return postId;
    }

    public String getPostProfile() {
        return postProfile;
    }

    public String getPostDesc() {
        return postDesc;
    }

    public Integer getReqExperience() {
        return reqExperience;
    }

    public List<String> getPostTechStack() {
        return postTechStack;
    }
}
