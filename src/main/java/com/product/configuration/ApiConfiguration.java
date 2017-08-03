package com.product.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by kshah on 7/31/17.
 */

@EnableSwagger2
@Configuration
public class ApiConfiguration {
    @Bean
    Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo("Product Service", "Product Service", "1.0", "", new Contact("ketul", "", "ketul.shah@aig.com"), "", ""));
    }

}
