package com.jwt.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.spi.service.contexts.SecurityContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Anuj kumar pal
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api()
    {
        ParameterBuilder userIdKey = new ParameterBuilder();
        userIdKey.name("loggedInUserId").modelRef(new ModelRef("Long")).parameterType("header").required(true).description("Logged-in user Id").build();

        List<Parameter> aParameters = new ArrayList<Parameter>();
         aParameters.add(userIdKey.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jwt.jwt.controller"))
                .paths(PathSelectors.regex("/.*"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(aParameters)
                .securitySchemes(Arrays.asList(securityToken()))
                .securityContexts(Arrays.asList(securityContext()))
                .apiInfo(getAppInfo());


    }


    private ApiInfo getAppInfo() {
        return new ApiInfoBuilder().title(" Java Web Token Implementation  ")
                .description("Welcomes your learn JWT implementation : Login, Signup, Logout etc ")
                .termsOfServiceUrl("https://callus.herokuapp.com/anujKumarPalResume").build();

    }
    private ApiKey securityToken()
    {
        return new ApiKey("Token", "Authorization", "header");
    }

    private SecurityContext securityContext()
    {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/.*")).build();
    }

    private List<SecurityReference> defaultAuth()
    {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Token", authorizationScopes));
    }
}
