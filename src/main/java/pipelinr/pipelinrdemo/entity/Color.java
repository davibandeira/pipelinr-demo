package pipelinr.pipelinrdemo.entity;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * The entity Color.
 */
@Entity
@Table(name = "colors")
public class Color implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank(message = "Color.name cannot be blank.")
  @Column(unique = true, nullable = false)
  private String name;

  @Column(name = "created_date", nullable = false, updatable = false)
  private OffsetDateTime createdDate;

  @Column(name = "updated_date")
  private OffsetDateTime updatedDate;

  /**
   * On before persist.
   */
  @PrePersist
  void onBeforePersist() {
    this.createdDate = OffsetDateTime.now(ZoneOffset.UTC);
  }

  /**
   * On before update.
   */
  @PreUpdate
  void onBeforeUpdate() {
    this.updatedDate = OffsetDateTime.now(ZoneOffset.UTC);
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(final Integer id) {
    this.id = id;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Gets created date.
   *
   * @return the created date
   */
  public OffsetDateTime getCreatedDate() {
    return createdDate;
  }

  /**
   * Sets created date.
   *
   * @param createdDate the created date
   */
  public void setCreatedDate(final OffsetDateTime createdDate) {
    this.createdDate = createdDate;
  }

  /**
   * Gets updated date.
   *
   * @return the updated date
   */
  public OffsetDateTime getUpdatedDate() {
    return updatedDate;
  }

  /**
   * Sets updated date.
   *
   * @param updatedDate the updated date
   */
  public void setUpdatedDate(final OffsetDateTime updatedDate) {
    this.updatedDate = updatedDate;
  }
}
