# Pipelinr demo
Example project to show how [Pipelinr](https://github.com/sizovs/PipelinR) can be used in a Spring Boot REST service.

## Technical Requirements
- Java JDK 11
- Maven 3.6.0 or newer

Simply run `mvn clean verify` and once the build is complete, run `java -jar target/pipelinr-demo-1.0.0-SNAPSHOT.jar`. You can now make
a POST request to `http://localhost:8080/colors` sending a JSON in the body with a content similar to:

```json
{
  "name": "Black"
}
```