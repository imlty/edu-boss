package com.lagou.edupayboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient // 注册到中心的客户端
public class EduPayBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduPayBootApplication.class, args);
    }

}
