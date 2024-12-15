package com.learn.spring.boot.jdbc_postgres;

import com.learn.spring.boot.jdbc_postgres.domain.Author;
import com.learn.spring.boot.jdbc_postgres.domain.Book;

// Utility classes are usually final
public final class TestDataUtil {

    //Private default constructor
    private TestDataUtil() {
    }


    /*
     * Importing the author class from of main/domain and creating an instance of it using builder patter
     * We have implemented builder pattern for our Author class using @Builder annotation
     * */
    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(2L)
                .name("Robin Sharma")
                .age(45)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(3L)
                .name("Agatha Christie")
                .age(95)
                .build();
    }



    public static Book createTestBook() {
        return Book.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookA() {
        return Book.builder()
                .isbn("978-1-2345-6789-1")
                .title("The Monk Who Sold His Ferrari")
                .authorId(2L)
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .isbn("978-1-2345-6789-2")
                .title("The Murder in the Orient Express")
                .authorId(3L)
                .build();
    }
}
