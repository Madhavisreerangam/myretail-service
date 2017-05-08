# myretail-service
Java project for providing MyRetail Rest Services

Technology Stack:
Java 8, Maven, Mongo DB 3.4 and Spring Boot 1.5.3.

System Prerequisites:
Java 8 and also needs local mongo db running for myretail service

Seeting up workspace and creating jar
From eclipse import as existing maven projects and run the following commands
mvn clean, install
Executable jar myretail-service-1.0.0.jar created in target folder.

To start the application
java -jar myretail-service-1.0.0.jar

REST Services:
GET - http://localhost:8080/myretail/product/get/{productId}
GET - http://localhost:8080/myretail/name/get/{productId}
PUT - http://localhost:8080/myretail/product/put/