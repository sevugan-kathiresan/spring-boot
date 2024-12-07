package com.learn_spring_boot.dependency_injection_annotation.services.implementations;

import com.learn_spring_boot.dependency_injection_annotation.services.BluePrinter;
import com.learn_spring_boot.dependency_injection_annotation.services.ColourPrinter;
import com.learn_spring_boot.dependency_injection_annotation.services.GreenPrinter;
import com.learn_spring_boot.dependency_injection_annotation.services.RedPrinter;
import org.springframework.stereotype.Service;

/*
 * Instead of using @Service annotation we can also use @Component bot does the same job by indicating to the Spring Boot
 * That it is a Bean.
* */

@Service
public class ColourPrinterImplementation implements ColourPrinter {

    // Defining Attributes
    private RedPrinter redPrinter;
    private BluePrinter bluePrinter;
    private GreenPrinter greenPrinter;

    public ColourPrinterImplementation(RedPrinter redPrinter, BluePrinter bluePrinter, GreenPrinter greenPrinter) {
        this.redPrinter = redPrinter;
        this.bluePrinter = bluePrinter;
        this.greenPrinter = greenPrinter;
    }

    @Override
    public String print() {
        return String.join(",", redPrinter.print(),bluePrinter.print(), greenPrinter.print());
    }
}
