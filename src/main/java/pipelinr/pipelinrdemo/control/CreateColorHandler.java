package pipelinr.pipelinrdemo.control;

import java.text.MessageFormat;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import an.awesome.pipelinr.Command;
import pipelinr.pipelinrdemo.entity.Color;
import pipelinr.pipelinrdemo.entity.commands.ColorResponseCommand;
import pipelinr.pipelinrdemo.entity.commands.CreateColorRequestCommand;

/**
 * Handles the creation of a {@link Color}.
 */
@Component
public class CreateColorHandler implements
    Command.Handler<CreateColorRequestCommand, ColorResponseCommand> {

  private final ColorRepository repository;

  public CreateColorHandler(final ColorRepository repository) {
    this.repository = repository;
  }

  @Override
  public ColorResponseCommand handle(final CreateColorRequestCommand command) {
    var color = new Color();
    color.setName(command.getName());
    try {
      var createdColor = repository.save(color);
      return ColorResponseCommand.from(createdColor);
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(
          HttpStatus.CONFLICT,
          MessageFormat
              .format("A Color with name [{0}] already exists.",
                  command.getName()));
    }
  }
}