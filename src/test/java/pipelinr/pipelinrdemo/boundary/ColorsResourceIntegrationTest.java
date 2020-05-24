package pipelinr.pipelinrdemo.boundary;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import pipelinr.pipelinrdemo.BaseContainerTest;

/**
 * Integration tests for {@link ColorsResource}.
 */
class ColorsResourceIntegrationTest extends BaseContainerTest {

  private static final String COLORS_PATH = "colors";

  @Autowired
  protected ObjectMapper jsonMapper;

  @Test
  void testCreateEndpointReturnsCreatedColor() throws JsonProcessingException {
    var colorName = "Black";
    var requestMap = Map.of("name", colorName);
    // @formatter:off
    given().
        body(jsonMapper.writeValueAsString(requestMap)).
        contentType(ContentType.JSON).
    when().
        post(COLORS_PATH).
    then().
        log().
        ifError().
        assertThat().
        statusCode(HttpStatus.CREATED.value()).
        header(HttpHeaders.LOCATION, response -> equalTo(resolveUrl(COLORS_PATH, response.path("id").toString()))).
        body("name", equalTo(colorName));
    // @formatter:on
  }

  @ParameterizedTest
  @EmptySource
  @ValueSource(strings = " ")
  void testCreateEndpointReturnsBadRequestWhenColorNameIsMissing(String colorName)
      throws JsonProcessingException {
    var requestMap = Map.of("name", colorName);
    // @formatter:off
    given().
        body(jsonMapper.writeValueAsString(requestMap)).
        contentType(ContentType.JSON).
    when().
        post(COLORS_PATH).
    then().
        log().
        ifStatusCodeMatches(not(HttpStatus.BAD_REQUEST.value())).
        assertThat().
        statusCode(HttpStatus.BAD_REQUEST.value());
    // @formatter:on
  }

}