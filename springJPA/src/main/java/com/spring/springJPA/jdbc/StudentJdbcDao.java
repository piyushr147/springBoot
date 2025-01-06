package com.spring.springJPA.jdbc;

import com.spring.springJPA.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentJdbcDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Student> findAll(){
        return jdbcTemplate.query("select * from student",new BeanPropertyRowMapper<>(Student.class));
    }

    public Student findById(int id){
        return jdbcTemplate.queryForObject("select * from student where id=?",new StudentRowMapper(),id);
    }
}
