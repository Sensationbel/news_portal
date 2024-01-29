package by.bulaukin.news_portal.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openApiDescription() {

        Server localHostServer = new Server();
        localHostServer.setUrl("http://localhost:8081");
        localHostServer.setDescription("Local ENVIRONMENT");

        Contact contact = new Contact();
        contact.setName("Dmitry Bulaukin");
        contact.setEmail("dbulavkin@gmail.com");

        License mitLicense = new License()
                .name("GNU AGPLv3")
                .url("https://choosealicense.com/license/agpl-3.0/");

        Info info = new Info()
                .title("News portal Api")
                .version("1.0")
                .contact(contact)
                .description("Api for News portal")
                .termsOfService("https://some.terms.url")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(localHostServer));
    }

}
