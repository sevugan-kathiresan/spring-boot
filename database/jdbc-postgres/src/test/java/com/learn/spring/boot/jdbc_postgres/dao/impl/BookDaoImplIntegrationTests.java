package com.learn.spring.boot.jdbc_postgres.dao.impl;

import com.learn.spring.boot.jdbc_postgres.TestDataUtil;
import com.learn.spring.boot.jdbc_postgres.domain.Author;
import com.learn.spring.boot.jdbc_postgres.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTests {

    private AuthorDaoImpl underTestSupport;
    private BookDaoImpl underTest;

    /*
    * Constructor Injection
    * */
    @Autowired
    public BookDaoImplIntegrationTests(BookDaoImpl underTest, AuthorDaoImpl underTestSupport) {
        this.underTest = underTest;
        this.underTestSupport = underTestSupport;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        /*
        * We are creating author first using test support class mainly because of two reasons
        * 1. After Each test the database and context will be cleared as we are using @DirtiesContext annotation
        * 2. The book as a foreign key constraint on Author
        * */
        Author author = TestDataUtil.createTestAuthor();
        underTestSupport.create(author);
        Book book = TestDataUtil.createTestBook();
        underTest.create(book);
        Optional<Book> result = underTest.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatManyBooksCanBeCreatedAndRecalled() {
        // we are testing three books so we need corresponding three authors, but instead of creating 3 authors we are modifying the
        // authorId property of the 3 books to point out to the one author that we have created
        // This can be achieved using setters obtained from builder pattern
        Author author = TestDataUtil.createTestAuthor();
        underTestSupport.create(author);

        Book book1 = TestDataUtil.createTestBook();
        book1.setAuthorId(author.getId());
        underTest.create(book1);
        Book book2 = TestDataUtil.createTestBookA();
        book2.setAuthorId(author.getId());
        underTest.create(book2);
        Book book3 = TestDataUtil.createTestBookB();
        book3.setAuthorId(author.getId());
        underTest.create(book3);

        List<Book> result = underTest.find();
        assertThat(result)
                .hasSize(3)
                .containsExactly(book1, book2, book3);

    }

    @Test
    public void testThatBookCanBeUpdated() {
        // Create the book which needs an author as prerequisite
        Author author = TestDataUtil.createTestAuthor();
        underTestSupport.create(author);
        Book book = TestDataUtil.createTestBook();
        book.setAuthorId(author.getId());
        underTest.create(book);

        // Update the book
        book.setTitle("Updated");
        underTest.update(book.getIsbn(), book);

        // Read back the updated version
        Optional<Book> result = underTest.findOne(book.getIsbn());

        //Assertions
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);

    }

    @Test
    public void testThatBookCanBeDeleted() {
        // Create the Author and book
        Author author = TestDataUtil.createTestAuthor();
        underTestSupport.create(author);
        Book book = TestDataUtil.createTestBook();
        book.setAuthorId(author.getId());
        underTest.create(book);

        // Delete the book
        underTest.delete(book.getIsbn());

        // Try finding the deleted book
        Optional<Book> result = underTest.findOne(book.getIsbn());

        assertThat(result).isEmpty();



    }
}
