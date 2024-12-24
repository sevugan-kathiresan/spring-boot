package com.learn.spring.boot.spring_jpa.repositories;

import com.learn.spring.boot.spring_jpa.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {
}
