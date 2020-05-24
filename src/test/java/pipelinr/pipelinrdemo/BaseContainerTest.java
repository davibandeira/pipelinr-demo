package pipelinr.pipelinrdemo;

import java.util.StringJoiner;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.restassured.RestAssured;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    classes = PipelinrDemoApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = Replace.ANY)
public abstract class BaseContainerTest {

  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.baseURI = getUrl();
  }

  protected String getUrl() {
    return "http://localhost:" + port;
  }

  protected String resolveUrl(String... relPath) {
    StringJoiner builder = new StringJoiner("/");
    builder.add(getUrl());
    for (var path : relPath) {
      builder.add(StringUtils.strip(path, "/"));
    }
    return builder.toString();
  }

}
