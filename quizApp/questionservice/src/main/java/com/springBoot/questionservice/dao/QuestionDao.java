package com.springBoot.questionservice.dao;


import com.springBoot.questionservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);

    @Query(value="SELECT * FROM question WHERE question.category=:category ORDER BY RANDOM() limit :number",nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int number);

    @Query(value="SELECT id FROM question WHERE question.category=:category ORDER BY RANDOM() limit :number",nativeQuery = true)
    List<Integer> findQuestionIDsByCategoryAndLimit(String category, int number);
}
