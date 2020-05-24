package pipelinr.pipelinrdemo.control;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.text.MessageFormat;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.server.ResponseStatusException;

import pipelinr.pipelinrdemo.entity.Color;
import pipelinr.pipelinrdemo.entity.commands.CreateColorRequestCommand;

/**
 * Test class for {@link CreateColorHandler}.
 */
class CreateColorHandlerTest {

  private ColorRepository repository;
  private CreateColorHandler handler;

  @BeforeEach
  void setUp() {
    repository = mock(ColorRepository.class);
    handler = new CreateColorHandler(repository);
  }

  @Test
  void handleShouldReturnAColorWithId() {
    // Given
    var color = spy(new Color());
    color.setId(1);
    color.setName("test");
    when(color.getCreatedDate()).thenReturn(OffsetDateTime.now());
    when(repository.save(any(Color.class))).thenReturn(color);

    // When
    var createdColor = handler.handle(new CreateColorRequestCommand("test"));

    // Then
    assertThat(createdColor.getId()).isEqualTo(color.getId());
    assertThat(createdColor.getName()).isEqualTo(color.getName());
    assertThat(createdColor.getCreatedDate()).isEqualTo(color.getCreatedDate());
  }

  @Test
  void handleShouldThrowApiExceptionWhenSaveThrowsDataIntegrityViolation() {
    // Given
    when(repository.save(any(Color.class))).thenThrow(DataIntegrityViolationException.class);
    var name = "Red";

    // When / Then
    assertThatExceptionOfType(ResponseStatusException.class)
        .isThrownBy(() -> handler.handle(new CreateColorRequestCommand(name)))
        .withMessageContaining(
            MessageFormat.format("A Color with name [{0}] already exists.", name));
  }

}