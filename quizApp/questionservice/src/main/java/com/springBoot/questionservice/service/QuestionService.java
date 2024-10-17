package com.springBoot.questionservice.service;


import com.springBoot.questionservice.dao.QuestionDao;
import com.springBoot.questionservice.model.Response;
import com.springBoot.questionservice.model.Question;
import com.springBoot.questionservice.model.QuestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
    };

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Success",HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failure",HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String questionsCategory, int numOfQuestions) {
        try{
            List<Integer> listOfQuestionIDs = questionDao.findQuestionIDsByCategoryAndLimit(questionsCategory,numOfQuestions);
            return new ResponseEntity<>(listOfQuestionIDs,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsByID(List<Integer> questionIDs) {
        try{
            List<QuestionWrapper> questionWrapperList = new ArrayList<>();
            for(Integer id:questionIDs){
                Optional<Question> question = questionDao.findById(id);
                QuestionWrapper questionWrapper = new QuestionWrapper(question.get().getId(),question.get().getQuestionTitle(),question.get().getOption1(),question.get().getOption2(),question.get().getOption3(),question.get().getOption4());
                questionWrapperList.add(questionWrapper);
            }
            return new ResponseEntity<>(questionWrapperList,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right = 0;
        for(Response response : responses){
            Question question = questionDao.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer()))
                right++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
