//package com.echochain.EchoChainAPI;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import com.google.common.base.Predicate;
//
//import static com.google.common.base.Predicates.or;
//import static springfox.documentation.builders.PathSelectors.regex;
//
//@Configuration
//public class SwaggerConfig {
//
//    @Bean
//    public Docket postsApi(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("Recipe-API")
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.gcu"))
//                .paths(paths()).build();
//    }
//    private Predicate<String> paths() {
//        return or(regex("/.*"), regex("/.*"));
//    }
//    private ApiInfo apiInfo(){
//        return new ApiInfoBuilder()
//                .title("Recipe-Api public-api")
//                .description("This is a recipe api documented with swagger")
//                .termsOfServiceUrl("adwawdadw").contact("info@info.com")
//                .license("Jack-Blackwell License").licenseUrl("awdawd")
//                .version("1.0").build();
//    }
//}
