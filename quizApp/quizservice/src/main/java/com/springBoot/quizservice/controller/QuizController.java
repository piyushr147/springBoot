package com.springBoot.quizservice.controller;

import com.springBoot.quizservice.model.QuestionWrapper;
import com.springBoot.quizservice.model.Quiz;
import com.springBoot.quizservice.model.Response;
import com.springBoot.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("")
    public ResponseEntity<Quiz> createQuiz(@RequestParam String category, @RequestParam int number, @RequestParam String title){
        return quizService.createQuiz(category,number,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable("id") int quizID){
        return quizService.getQuizQuestions(quizID);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable("id") int quizID, @RequestBody List<Response> resposnes){
        return quizService.getScore(resposnes);
    }
}
