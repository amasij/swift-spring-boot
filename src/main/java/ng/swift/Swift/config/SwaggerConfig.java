package ng.swift.Swift.config;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Swift Aplication API").version("1.0")
                        .contact(contact())
                        .description(
                                "The Swift RESTful service using springdoc-openapi and OpenAPI 3."));
    }

    private Contact contact() {
        Contact contact = new Contact();
        contact.setName("Simon Joseph");
        contact.setEmail("simonjoseoh750@gmail.com");
        return contact;
    }
}
