package app;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import io.swagger.v3.jaxrs2.integration.JaxrsOpenApiContextBuilder;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.integration.OpenApiConfigurationException;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.extern.log4j.Log4j2;

@ApplicationPath("api")
@Log4j2
public class RestAPI extends ResourceConfig {

	public RestAPI() {
		packages("resource");

		log.debug("Lancement du ressource config de l'application.");

		log.debug("Création de l'OpenAPI (Swagger).");
		OpenAPI openApi = new OpenAPI();
		Info info = new Info().title("Personne Open API").description("Documentation de l'API Personne.")
				.contact(new Contact().email("personne@mycompany.com")).version("0.0.1");
		openApi.info(info);
		openApi.schemaRequirement("bearer-auth",
				new SecurityScheme().scheme("BEARER").description("JWT authentication with bearer token.")
						.name("Authorization").type(SecurityScheme.Type.APIKEY).bearerFormat("BEARER [token]")
						.in(SecurityScheme.In.HEADER));
		log.debug("Open API info. " + openApi.toString());
		Server server = new Server();
		server.setUrl("http://localhost:8080/PersonneProject/");
		openApi.addServersItem(server);
		log.debug("Server URL : " + server.getUrl());
		SwaggerConfiguration oasConfig = new SwaggerConfiguration()
				.resourcePackages(
						Stream.of("resource").collect(Collectors.toSet()))
				.prettyPrint(true).cacheTTL(0L).scannerClass("io.swagger.v3.jaxrs2.integration.JaxrsAnnotationScanner")
				.readAllResources(false).openAPI(openApi);
		log.debug("Swagger Configuration : " + oasConfig.getResourcePackages().toString());
		OpenApiResource openApiResource = new OpenApiResource();
		openApiResource.setOpenApiConfiguration(oasConfig);
		register(openApiResource);
		try {
			new JaxrsOpenApiContextBuilder().application(this.getApplication()).openApiConfiguration(oasConfig)
					.buildContext(true);
		} catch (OpenApiConfigurationException e) {
			log.error("Error on swagger config.");
			throw new RuntimeException(e.getMessage(), e);
		}
		log.debug("Fin de la génération de l'OpenAPI descriptor.");

	}

}

