package com.spring.springJPA.jdbc;

import com.spring.springJPA.entity.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getInt("id"));
        person.setLocation(rs.getString("location"));
        person.setName(rs.getString("name"));
        person.setBirthDate(rs.getTimestamp("birth_date").toLocalDateTime());
        return person;
    }
}
