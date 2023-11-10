package com.ohgiraffers.restapi.section05.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.HashSet;
import java.util.Set;

@Configuration // 빈 등록
@EnableWebMvc
public class SwaggerConfig {

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder()
                .title("Ohgiraffers API")
                .description("spring boot swagger 연동 테스트")
                .build();
    }

    private Set<String> getConsumeContentTypes () {
        // 소비할 컨텐츠 타입
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
        consumes.add("application/x-www.form-urlencoded");
        return consumes;
    }

    private Set<String> getProduceContentTypes() {
        // 제공할 컨텐츠 타입
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .consumes(getConsumeContentTypes())
                .produces(getProduceContentTypes())
                .apiInfo(swaggerInfo())
                .select()
                // 모든 경로를 API 문서화
                //.apis(RequestHandlerSelectors.any())
                // 지정 된 패키지만 API 문서화
                .apis(RequestHandlerSelectors.basePackage("com.ohgiraffers.restapi.section05"))
                // 모든 URL 패턴에 대해 API 문서화
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false);
    }
}
