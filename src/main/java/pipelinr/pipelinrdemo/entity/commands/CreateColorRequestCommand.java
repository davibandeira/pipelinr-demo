package pipelinr.pipelinrdemo.entity.commands;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

import an.awesome.pipelinr.Command;
import pipelinr.pipelinrdemo.entity.Color;

/**
 * Represents the HTTP request for creating a new {@link Color}.
 */
public final class CreateColorRequestCommand implements Command<ColorResponseCommand> {

  @NotBlank(message = "Name must not be blank.")
  private final String name;

  @JsonCreator
  public CreateColorRequestCommand(final String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}