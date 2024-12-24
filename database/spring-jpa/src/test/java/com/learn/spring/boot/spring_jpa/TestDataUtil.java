package com.learn.spring.boot.spring_jpa;

import com.learn.spring.boot.spring_jpa.domain.Author;
import com.learn.spring.boot.spring_jpa.domain.Book;

public class TestDataUtil {
    private TestDataUtil(){
    }

    /*
    * While creating authors we are defining the id field as null, because id is annotated as autogenerated sequence
    * So hibernate will take care of the value allocation
    * If we interfere in that it will create optimistic locking exception
    * */

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(null)
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(null)
                .name("Thomas Cronin")
                .age(44)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(null)
                .name("Jesse A Casey")
                .age(24)
                .build();
    }

    public static Book createTestBookA(final Author author) {
        return Book.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .author(author)
                .build();
    }

    public static Book createTestBookB(final Author author) {
        return Book.builder()
                .isbn("978-1-2345-6789-1")
                .title("Beyond the Horizon")
                .author(author)
                .build();
    }

    public static Book createTestBookC(final Author author) {
        return Book.builder()
                .isbn("978-1-2345-6789-2")
                .title("The Last Ember")
                .author(author)
                .build();
    }
}
