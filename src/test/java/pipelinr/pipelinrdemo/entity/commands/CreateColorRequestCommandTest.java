package pipelinr.pipelinrdemo.entity.commands;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link CreateColorRequestCommand}.
 */
class CreateColorRequestCommandTest {

  @Test
  void getNameShouldReturnThePassedValue() {
    // Given
    var expectedName = "name";
    var color = new CreateColorRequestCommand(expectedName);

    // When
    var name = color.getName();

    // Then
    assertThat(name).isEqualTo(expectedName);
  }

}