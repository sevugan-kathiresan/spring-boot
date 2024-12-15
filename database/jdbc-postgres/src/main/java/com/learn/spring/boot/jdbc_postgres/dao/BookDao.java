package com.learn.spring.boot.jdbc_postgres.dao;

import com.learn.spring.boot.jdbc_postgres.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    public void create(Book book);
    public Optional<Book> findOne(String isbn);
    public List<Book> find();
    public void update(String isbn, Book book);
    public void delete(String isbn);
}
