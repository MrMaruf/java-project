package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class HelloWorldApplication {
    private static final String PORT = "8080";

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(HelloWorldApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", PORT));
        app.run(args);

    }
}