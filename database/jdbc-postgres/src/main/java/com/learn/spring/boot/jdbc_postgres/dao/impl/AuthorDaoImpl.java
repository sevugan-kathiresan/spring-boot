package com.learn.spring.boot.jdbc_postgres.dao.impl;

import com.learn.spring.boot.jdbc_postgres.dao.AuthorDao;
import com.learn.spring.boot.jdbc_postgres.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl (final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Author author) {
        jdbcTemplate.update(
                "INSERT INTO authors (id, name, age) VALUES (?, ?, ?)",
                author.getId(),  author.getName(), author.getAge()
        );
    }

    @Override
    public Optional<Author> findOne(long authorId) {
        /*
        * We are storing the results as List<Authors> even after putting the constraint of LIMIT 1 in SQL query
        * because the JdbcTemplate is designed to return result as list even if it is just one row
        * */
        List<Author> results = jdbcTemplate.query(
                "SELECT id, name, age FROM authors WHERE id = ? LIMIT 1",
                new AuthorRowMapper(), authorId
        );

        return results.stream().findFirst();
    }

    @Override
    public List<Author> find() {
        return jdbcTemplate.query(
                "SELECT id, name, age FROM authors",
                new AuthorRowMapper()
                );
    }

    @Override
    public void update(long id, Author author) {
        jdbcTemplate.update(
                "UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?",
                author.getId(), author.getName(), author.getAge(), id
        );
    }

    @Override
    public void delete(long id) {

        jdbcTemplate.update(
                "DELETE FROM authors WHERE id = ?",
                id
        );
    }

    /*
    * our custom row mapper class which implements RowMapper
    * RowMapper is used to map from sql result to Java objects
    * Since we are using Jdbc we need to do this conversion manually
    * */
    public static class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();

        }
    }
}
