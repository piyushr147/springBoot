package com.springBoot.JobApp.service;

import com.springBoot.JobApp.model.JobPost;
import com.springBoot.JobApp.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {
    @Autowired
    public JobRepository jobRepository;

    public List<JobPost> getAllJobs() {
        return jobRepository.findAll();
    }

    public JobPost getJob(int postId) {
        return jobRepository.findById(postId).orElse(new JobPost());
    }

    public void addPost(JobPost jobPost) {
        jobRepository.save(jobPost);
    }

    public void updateJob(JobPost jobPost) {
        jobRepository.save(jobPost);
    }

    public void deletePost(int postId) {
        jobRepository.deleteById(postId);
    }

    public void load() {
        List<JobPost> jobs = new ArrayList<>();
        jobs.add(new JobPost(1, "Java Developer", "Must have good experience in core Java and advanced Java", 2,
                List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")));
        jobs.add(
                new JobPost(2, "Frontend Developer", "Experience in building responsive web applications using React",
                        3, List.of("HTML", "CSS", "JavaScript", "React")));
        jobs.add(new JobPost(3, "Data Scientist", "Strong background in machine learning and data analysis", 4,
                List.of("Python", "Machine Learning", "Data Analysis")));

        jobs.add(new JobPost(4, "Network Engineer",
                "Design and implement computer networks for efficient data communication", 5,
                List.of("Networking", "Cisco", "Routing", "Switching")));

        jobs.add(new JobPost(5, "Mobile App Developer", "Experience in mobile app development for iOS and Android",
                        3, List.of("iOS Development", "Android Development", "Mobile App")));
        jobRepository.saveAll(jobs);
    }
}
