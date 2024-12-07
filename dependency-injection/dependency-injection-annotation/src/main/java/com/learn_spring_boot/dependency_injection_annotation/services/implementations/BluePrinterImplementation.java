package com.learn_spring_boot.dependency_injection_annotation.services.implementations;

import com.learn_spring_boot.dependency_injection_annotation.services.BluePrinter;

import org.springframework.stereotype.Service;

/*
* Instead of using @Service annotation we can also use @Component bot does the same job by indicating to the Spring Boot
* That it is a Bean.
* */
@Service
public class BluePrinterImplementation implements BluePrinter {

    @Override
    public String print() {
        return "Blue";
    }
}
