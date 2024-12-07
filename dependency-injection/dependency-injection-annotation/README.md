## Dependency Injection with Beans using Annotation

This is a code example that describes how to work with dependency injection in Spring Boot application.

### Description:
 - The application simply prints three colour names "Red", "Blue", "Green".
 - The main function has a dependency of one bean `ColourPrinter`.
 - `ColourPrinter` is dependent on 3 other beans `RedPrinter`, `GreenPrinter`, `BluePrinter`.
 - Each of these four beans has their own interfaces as well as implementations.

Note: It is worth to note that even-though all the interfaces implement the same exact print method , it is a 
necessity that each bean should have a unique interface and a corresponding implementation.


