package pipelinr.pipelinrdemo.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Color}.
 */
class ColorTest {

  @Test
  void gettersShouldReturnNullByDefault() {
    var color = new Color();

    assertThat(color.getId()).isNull();
    assertThat(color.getName()).isNull();
    assertThat(color.getCreatedDate()).isNull();
    assertThat(color.getUpdatedDate()).isNull();
  }

  @Test
  void gettersShouldReturnTheValuesSetViaSetters() {
    var expectedId = 1;
    var expectedName = "colorName";
    var expectedCreatedDate = OffsetDateTime.now();
    var expectedUpdatedDate = OffsetDateTime.now();

    var color = new Color();

    color.setId(expectedId);
    color.setName(expectedName);
    color.setCreatedDate(expectedCreatedDate);
    color.setUpdatedDate(expectedUpdatedDate);

    assertThat(color.getId()).isEqualTo(expectedId);
    assertThat(color.getName()).isEqualTo(expectedName);
    assertThat(color.getCreatedDate()).isEqualTo(expectedCreatedDate);
    assertThat(color.getUpdatedDate()).isEqualTo(expectedUpdatedDate);
  }

  @Test
  void onBeforeUpdateShouldSetTheUpdatedDate() {
    var color = new Color();

    color.onBeforeUpdate();

    assertThat(color.getId()).isNull();
    assertThat(color.getName()).isNull();
    assertThat(color.getCreatedDate()).isNull();
    assertThat(color.getUpdatedDate()).isNotNull();
  }

  @Test
  void onBeforePersistShouldSetTheCreatedDate() {
    var color = new Color();

    color.onBeforePersist();

    assertThat(color.getId()).isNull();
    assertThat(color.getName()).isNull();
    assertThat(color.getUpdatedDate()).isNull();
    assertThat(color.getCreatedDate()).isNotNull();
  }
}