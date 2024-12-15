package com.learn.spring.boot.jdbc_postgres.dao;

import com.learn.spring.boot.jdbc_postgres.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    void create(Author author);

    /*
    * returns an Optional of Author if found a match otherwise returns
    * empty optional, more type safe than null
    * */
    Optional<Author> findOne(long l);
    List<Author> find();
    void update(long id, Author author);
    void delete(long id);


}
