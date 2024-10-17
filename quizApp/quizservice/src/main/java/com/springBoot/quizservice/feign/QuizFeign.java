package com.springBoot.quizservice.feign;

import com.springBoot.quizservice.model.QuestionWrapper;
import com.springBoot.quizservice.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@FeignClient("QUESTIONSERVICE")
public interface QuizFeign {
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String questionsCategory, @RequestParam int numOfQuestions);

    @GetMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsByID(@RequestBody List<Integer> questionIDs);

    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
