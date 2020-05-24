package pipelinr.pipelinrdemo.entity.commands;


import java.time.OffsetDateTime;

import pipelinr.pipelinrdemo.entity.Color;

/**
 * Represents a response for when a {@link Color} gets persisted or updated.
 */
public final class ColorResponseCommand {

  private final int id;
  private final String name;
  private final OffsetDateTime createdDate;

  private ColorResponseCommand(final int id, final String name, final OffsetDateTime createdDate) {
    this.id = id;
    this.name = name;
    this.createdDate = createdDate;
  }

  /**
   * Creates an instance of a {@link ColorResponseCommand} from the provided {@code color}.
   *
   * @param color the instance from which the values will be extracted.
   * @return a instance containing the right values.
   */
  public static ColorResponseCommand from(final Color color) {
    return new ColorResponseCommand(color.getId(), color.getName(), color.getCreatedDate());
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public OffsetDateTime getCreatedDate() {
    return createdDate;
  }
}