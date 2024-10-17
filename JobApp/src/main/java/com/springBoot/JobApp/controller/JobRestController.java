package com.springBoot.JobApp.controller;

import com.springBoot.JobApp.model.JobPost;
import com.springBoot.JobApp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobRestController {

    @Autowired
    public JobService jobService;

    @GetMapping("jobPosts")
    public List<JobPost> getAllJobs(){
        return jobService.getAllJobs();
    }

    @GetMapping("jobPost/{postId}")
    public JobPost getJob(@PathVariable("postId") int postId){
        return jobService.getJob(postId);
    }
    @PostMapping("jobPosts")
    public JobPost addJob(@RequestBody JobPost jobPost){
        jobService.addPost(jobPost);
        return jobService.getJob(jobPost.getPostId());
    }

    @PutMapping("jobPosts")
    public JobPost updatePost(@RequestBody JobPost jobPost){
        jobService.updateJob(jobPost);
        return jobService.getJob(jobPost.getPostId());
    }

    @DeleteMapping("jobPosts/{postId}")
    public String deletePost(@PathVariable("postId") int postId){
        jobService.deletePost(postId);
        if(jobService.getJob(postId) == null)
            return "successfully deleted the post ${postId}";
        else
            return "Failure in deletion";
    }

    @GetMapping("load")
    public String load(){
        jobService.load();
        return "success";
    }
}
