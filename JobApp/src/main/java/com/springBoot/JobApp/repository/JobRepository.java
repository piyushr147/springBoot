package com.springBoot.JobApp.repository;

import com.springBoot.JobApp.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<JobPost, Integer> {
//    List<JobPost> jobs = new ArrayList<>();
//
//    public JobRepo() {
//        jobs.add(new JobPost(1, "Java Developer", "Must have good experience in core Java and advanced Java", 2,
//                List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")));
//        jobs.add(
//                new JobPost(2, "Frontend Developer", "Experience in building responsive web applications using React",
//                        3, List.of("HTML", "CSS", "JavaScript", "React")));
//        jobs.add(new JobPost(3, "Data Scientist", "Strong background in machine learning and data analysis", 4,
//                List.of("Python", "Machine Learning", "Data Analysis")));
//
//        jobs.add(new JobPost(4, "Network Engineer",
//                "Design and implement computer networks for efficient data communication", 5,
//                List.of("Networking", "Cisco", "Routing", "Switching")));
//
//        jobs.add(new JobPost(5, "Mobile App Developer", "Experience in mobile app development for iOS and Android",
//                        3, List.of("iOS Development", "Android Development", "Mobile App")));
//    }
//    public List<JobPost> getAllJobs() {
//        return jobs;
//    }
//    public void addJobPost(JobPost job) {
//        jobs.add(job);
//    }
//    public List<JobPost> getAll() {
//        return null;
//    }
//
//    public JobPost getJob(int postId) {
//        for(JobPost job: jobs){
//            if(job.getPostId() == postId)
//                return job;
//        }
//        return null;
//    }
//
//    public void addPost(JobPost jobPost) {
//        jobs.add(jobPost);
//    }
//
//    public void updatePost(JobPost jobPost) {
//        for(JobPost job: jobs){
//            if(job.getPostId() == jobPost.getPostId()){
//                job.setPostDesc(jobPost.getPostDesc());
//                job.setPostProfile(jobPost.getPostProfile());
//                job.setPostTechStack(jobPost.getPostTechStack());
//                job.setReqExperience(jobPost.getReqExperience());
//            }
//        }
//    }
//    public void deletePost(int postId) {
//        jobs.removeIf(job -> job.getPostId() == postId);
//    }
}