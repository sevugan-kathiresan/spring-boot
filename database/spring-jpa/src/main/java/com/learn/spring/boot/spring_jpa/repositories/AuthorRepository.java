package com.learn.spring.boot.spring_jpa.repositories;

import com.learn.spring.boot.spring_jpa.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    /*
    * We are not going to provide implementation to this method. Spring Data JPA will look in to the name of the method
    * and provides the implementation it will look in to the name "ageLessThan" and Look in to the argument "age" and figure the query on its own.
    * */
    Iterable<Author> ageLessThan(int age);

    /*
    * In HQL (Hibernate Query Language)  the syntax is based on Entity not the Table name that is why in the below query we used the term "Author" even-though the table name in authors
    * */
    @Query("SELECT a FROM Author a WHERE a.age > ?1")
    Iterable<Author> findAuthorsWithAgeGreaterThan(int age);
}
