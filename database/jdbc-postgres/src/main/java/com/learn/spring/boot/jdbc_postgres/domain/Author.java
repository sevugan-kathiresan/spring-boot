package com.learn.spring.boot.jdbc_postgres.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* @Data - generates getters, setters, toString(), equals(), hasCode()
* @AllArgsConstructor - Generates a constructor which accepts all fields as parameters
* @NoArgsConstructor - Generates a default constructor (with no parameters) - useful in case of using JPA and Hibernate
   - As JPA & Hibernate does not know the structure of the entity in the database during the compile time because the interaction
     only happens at runtime
* @Builder: Generates a builder pattern for constructing objects with optional parameters
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {

    private Long id; // Long supports null, but long does not it defaults to 0, in order to get better consistency with database object we are using Long
    private String name;
    private Integer age; // Integer defaults to null, but int does not
}
