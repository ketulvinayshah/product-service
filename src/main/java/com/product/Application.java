package com.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 * Created by kshah on 8/15/16.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@SuppressWarnings("unused")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    @LoadBalanced
    @Bean
    public OAuth2RestTemplate restTemplate(OAuth2ProtectedResourceDetails details, OAuth2ClientContext clientContext){
        return new OAuth2RestTemplate(details, clientContext);
    }


}
