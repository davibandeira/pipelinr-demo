package pipelinr.pipelinrdemo.boundary;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import an.awesome.pipelinr.Pipeline;
import pipelinr.pipelinrdemo.entity.commands.ColorResponseCommand;
import pipelinr.pipelinrdemo.entity.commands.CreateColorRequestCommand;

/**
 * Colors resource.
 */
@RestController
@RequestMapping("colors")
public class ColorsResource {

  private final Pipeline pipeline;

  /**
   * Instantiates a new ColorsResource.
   *
   * @param pipeline the pipeline
   */
  public ColorsResource(final Pipeline pipeline) {
    this.pipeline = pipeline;
  }

  /**
   * Creates a new Color.
   *
   * @param command the command
   * @return the response entity
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ColorResponseCommand> insert(
      @Valid @RequestBody CreateColorRequestCommand command) {
    var data = pipeline.send(command);
    var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(data.getId()).toUri();
    return ResponseEntity.created(uri).body(data);
  }
}
