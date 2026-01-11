package COMP301Project.EventCatalogService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Event Catalog API",
                version = "1.0",
                description = "API documentation for managing events"
        )
)

@SpringBootApplication
public class EventCatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventCatalogServiceApplication.class, args);
    }

}
