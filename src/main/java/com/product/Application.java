package com.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by kshah on 8/15/16.
 */
@SpringBootApplication
@EnableDiscoveryClient
@SuppressWarnings("unused")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }


}
