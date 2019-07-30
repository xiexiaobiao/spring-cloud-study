package org.springframework.cloud.apizuulgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy//开启zuul的api网关服务
public class ApiZuulGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiZuulGatewayApplication.class, args);
    }

    @Bean
    PermisFilter permisFilter(){
        return new PermisFilter();
    }
}


