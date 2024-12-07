package com.learn_spring_boot.dependency_injection_annotation;

import com.learn_spring_boot.dependency_injection_annotation.services.ColourPrinter;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Log
public class DependencyInjectionAnnotationApplication implements CommandLineRunner {

	private ColourPrinter colourPrinter;

	public DependencyInjectionAnnotationApplication(ColourPrinter colourPrinter) {
		this.colourPrinter = colourPrinter;
	}

	public static void main(String[] args) {
		SpringApplication.run(DependencyInjectionAnnotationApplication.class, args);
	}

	/*
	The run method overrides the default run method as it is a requirement when implementing CommandlineRunner.

	final - keyword that makes the args variable constant so no reassigning possible with in the method

	String... - Indicates the String array of variable length using this instead of String[] gives us flexibility of
	calling the method without any arguments, so there is no mandatory need to pass in a String array as an argument.
	*/
	@Override
	public void run(final String... args) {
		log.info(colourPrinter.print());
	}

}
