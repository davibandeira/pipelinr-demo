package pipelinr.pipelinrdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "pipelinr.pipelinrdemo")
@EnableJpaRepositories
public class PipelinrDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(PipelinrDemoApplication.class, args);
  }

}
