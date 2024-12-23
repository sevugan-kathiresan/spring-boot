package com.learn.spring.boot.jdbc_postgres.dao.impl;

import com.learn.spring.boot.jdbc_postgres.TestDataUtil;
import com.learn.spring.boot.jdbc_postgres.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {

    /*
    * @InjectMocks creates a new instance of a specified class before every test and injects the specified mock (here Jdbc Template)
    * in to the newly created class.
    * */
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest;


    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {

        Author author = TestDataUtil.createTestAuthor();

        underTest.create(author);

        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
                eq(1L),eq("Abigail Rose"), eq(80)
        );
    }


    @Test
    public void testThatFindOneGeneratesCorrectSql() {
        underTest.findOne(1L);

        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),// Ensures the sql query generated by jdbcTemplate.query() inside findOne method in AuthorDao matches this
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(), // Ensures proper instance creation AuthorRowMapper as a second argument to dbcTemplate.query() inside findOne method in AuthorDao
                eq(1L) // Ensures the authorId parameter in the findOne method which is being passed to jdbcTemplate.query is equal to "1L"
        );
    }

    @Test
    public void testThatFindManyGeneratesCorrectSql() {
        underTest.find();

        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateGeneratesCorrectSql() {
        Author author = TestDataUtil.createTestAuthor();
        underTest.update(10L, author);

        verify(jdbcTemplate).update(
                eq("UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?"),
                eq(1L),eq("Abigail Rose"),eq(80), eq(10L)
        );

    }

    @Test
    public void testThatDeleteGeneratesCorrectSql() {
        underTest.delete(1L);

        verify(jdbcTemplate).update(
                eq("DELETE FROM authors WHERE id = ?"),
                eq(1L)
        );
    }
}
