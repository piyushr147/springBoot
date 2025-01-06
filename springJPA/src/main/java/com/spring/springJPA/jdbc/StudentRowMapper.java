package com.spring.springJPA.jdbc;

import com.spring.springJPA.entity.Identity;
import com.spring.springJPA.entity.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setLocation(rs.getString("location"));
        student.setName(rs.getString("name"));
        student.setBirthDate(rs.getTimestamp("birth_date").toLocalDateTime());
        Identity identity = new Identity();
        return student;
    }
}
