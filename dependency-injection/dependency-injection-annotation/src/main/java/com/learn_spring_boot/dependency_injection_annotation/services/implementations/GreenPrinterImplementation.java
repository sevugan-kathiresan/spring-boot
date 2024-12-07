package com.learn_spring_boot.dependency_injection_annotation.services.implementations;

import com.learn_spring_boot.dependency_injection_annotation.services.GreenPrinter;
import org.springframework.stereotype.Service;

/*
 * Instead of using @Service annotation we can also use @Component bot does the same job by indicating to the Spring Boot
 * That it is a Bean.
* */
@Service
public class GreenPrinterImplementation implements GreenPrinter {

    @Override
    public String print() {
        return "Green";
    }
}
