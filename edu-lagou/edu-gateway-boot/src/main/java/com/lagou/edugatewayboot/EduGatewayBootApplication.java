package com.lagou.edugatewayboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EduGatewayBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduGatewayBootApplication.class, args);
    }

}
