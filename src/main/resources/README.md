 [x] Add Spring Boot parent, MVC, and DevTools dependencies to pom.xml. (See lessons.)
 
 [ ] Add a @RestController to your project that supports all operations from your project's domain service class. (Hint: Don't write all methods and hope for the best when you run the application. Implement one method. Complete the rest of the steps. Confirm the method is working. Implement other methods and confirm one at a time.)
 
 [ ] Determine appropriate HTTP response statuses for each domain outcome. ResponseEntity<T> is helpful here, though it's perfectly acceptable to start by returning the raw output of each domain method. Focus on getting the controller working first.
 
 [ ] Configure the repositories DataSource in application.properties.
 
 [x] Modify your main method and class to launch with Spring Boot.
 
 [ ] Run the app. Exercise service endpoints with REST Client. With each operation, confirm the response is correct and data is updated in the database. (Use Workbench to confirm.)
 
 [ ] You've confirmed your JdbcTemplate repository is working through tests, so if something goes wrong there are two likely sources: Spring annotation configuration and application.properties settings. Scan error messages to determine the culprit.
 
