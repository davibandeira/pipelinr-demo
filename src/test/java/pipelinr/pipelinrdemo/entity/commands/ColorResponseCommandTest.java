package pipelinr.pipelinrdemo.entity.commands;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;

import pipelinr.pipelinrdemo.entity.Color;

/**
 * Test class for {@link ColorResponseCommand}.
 */
class ColorResponseCommandTest {

  @Test
  void constructorShouldSetPassedValues() {
    var color = new Color();
    color.setId(1);
    color.setName("name");
    color.setCreatedDate(OffsetDateTime.now());

    // When
    var response = ColorResponseCommand.from(color);

    // Then
    assertThat(response.getId()).isEqualTo(color.getId());
    assertThat(response.getName()).isEqualTo(color.getName());
    assertThat(response.getCreatedDate()).isEqualTo(color.getCreatedDate());
  }

}