package com.springBoot.quizservice.service;

import com.springBoot.quizservice.dao.QuizDao;
import com.springBoot.quizservice.feign.QuizFeign;
import com.springBoot.quizservice.model.Question;
import com.springBoot.quizservice.model.QuestionWrapper;
import com.springBoot.quizservice.model.Quiz;
import com.springBoot.quizservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;
    @Autowired
    private QuizFeign quizFeign;

    public ResponseEntity<Quiz> createQuiz(String category, int number, String title) {
        List<Integer> questionList = quizFeign.getQuestionsForQuiz(category,number).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsIDs(questionList);
        quizDao.save(quiz);

        return new ResponseEntity<>(quiz,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int quizID) {
        Optional<Quiz> quizData = quizDao.findById(quizID);
        List<Integer> questionList = quizData.get().getQuestionsIDs();
        List<QuestionWrapper> questionWrappers = quizFeign.getQuestionsByID(questionList).getBody();
        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int score = 0;
        score = quizFeign.getScore(responses).getBody();
        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
