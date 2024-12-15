package com.learn.spring.boot.jdbc_postgres.dao.impl;


import com.learn.spring.boot.jdbc_postgres.TestDataUtil;
import com.learn.spring.boot.jdbc_postgres.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


/*
* @SpringBootTest - Spins up a test version of our application
* SpringExtension.class - makes sure every spring functionality is supported in test version
* @DirtiesContext - clears the database and context after every test case helps to isolate the test functionality
* */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoImplIntegrationTests {

    private AuthorDaoImpl underTest;

    /*
    * Constructor injection technique
    * @Autowired - for test we need this annotation to tell spring explicitly to inject the dependency
    * */
    @Autowired
    public AuthorDaoImplIntegrationTests(AuthorDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthor();
        underTest.create(author);
        Optional<Author> result = underTest.findOne(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        Author author1 = TestDataUtil.createTestAuthor(); // Since the database and context is cleared after each test we can use the same method .createTestAuthor() again.
        underTest.create(author1);
        Author author2 = TestDataUtil.createTestAuthorA();
        underTest.create(author2);
        Author author3 = TestDataUtil.createTestAuthorB();
        underTest.create(author3);

        List<Author> result = underTest.find();
        assertThat(result)
                .hasSize(3)
                .containsExactly(author1,author2, author3);

    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        // Create an author
        Author author = TestDataUtil.createTestAuthor();
        underTest.create(author);

        // Update the author
        author.setName("Updated");
        underTest.update(author.getId(), author);

        // find the updated author
        Optional<Author> result = underTest.findOne(author.getId());

        // Assertions
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);

    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        // Create the author
        Author author = TestDataUtil.createTestAuthor();
        underTest.create(author);

        // Delete the author
        underTest.delete(author.getId());

        // Try to find the deleted author
        Optional<Author> result = underTest.findOne(author.getId());
        assertThat(result).isEmpty();

    }
}
