package com.administration.config;
import io.swagger.models.auth.In;
import io.swagger.v3.oas.models.security.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.builders.ApiInfoBuilder;

import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.builders.AuthorizationScopeBuilder;
import springfox.documentation.service.AuthorizationScope;

import springfox.documentation.service.SecurityReference;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.swagger.web.*;
import springfox.documentation.service.AuthorizationScope;



import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId(null)
                .clientSecret(null)
                .realm(null)
                .appName(null)
                .scopeSeparator(",")
                .additionalQueryStringParams(null)
                .useBasicAuthenticationWithAccessCodeGrant(false)
                .build();
    }



    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Arrays.asList(new ApiKey("JWT", HttpHeaders.AUTHORIZATION, In.HEADER.name())))
                .securityContexts(Arrays.asList(securityContext()))
                .apiInfo(apiInfo());
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[0])))
                .build();
    }
    private ApiInfo apiInfo () {
        return new ApiInfoBuilder()

                .title("Swagger Configuration")
                .description("\"Spring Boot Swagger configuration\"")
                .version("1.1.0").build();
    }
    //http://localhost:8088/swagger-ui/index.html
}
