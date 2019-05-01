package com.pibigstar.evaluation.common.config;

import com.pibigstar.evaluation.utils.ConfigUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import sun.security.krb5.Config;

/**
 * Swagger配置
 * @author pibigstar
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            //只有加了ApiOperation注解的类，才生成接口文档
            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
            //包下的类，生成接口文档
            //.apis(RequestHandlerSelectors.basePackage("com.pibigstar.web"))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title(ConfigUtils.getString("PROJECT_TITLE"))
            .description(ConfigUtils.getString("PROJECT_DESCRIPTION"))
            .termsOfServiceUrl(ConfigUtils.getString("PROJECT_URL"))
            .version(ConfigUtils.getString("PROJECT_VERSION"))
            .build();
    }

}