package tp.leothy.springboot.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI apiBibliotheque() {
        final String cleApiNom = "X-CLE-API";

        return new OpenAPI()
                .info(new Info()
                        .title("API Bibliothèque (FR, tp.leothy.springboot)")
                        .description("TP Spring Boot - API REST de gestion de bibliothèque (full FR, Lombok).")
                        .version("1.0.0"))
                .addSecurityItem(new SecurityRequirement().addList(cleApiNom))
                .components(new Components()
                        .addSecuritySchemes(cleApiNom, new SecurityScheme()
                                .name(cleApiNom)
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)));
    }
}
