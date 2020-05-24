package pipelinr.pipelinrdemo;


import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Command.Handler;
import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;

/**
 * Provides an instance of a {@link an.awesome.pipelinr.Pipeline}.
 */
@Configuration
public class PipelinrProvider {

  @Bean
  public Pipeline getPipeline(
      ObjectProvider<Handler> commandHandlers,
      ObjectProvider<Notification.Handler> notificationHandlers,
      ObjectProvider<Command.Middleware> middlewares) {
    return new Pipelinr()
        .with(commandHandlers::stream)
        .with(notificationHandlers::stream)
        .with(middlewares::orderedStream);
  }
}