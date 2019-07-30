package org.springframework.cloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient//开启注册中心发现
@EnableCircuitBreaker //开启hystrix功能
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    //这里放在入口类，是需要启动就加载这个bean
    @LoadBalanced//开启ribbon客户端负载均衡功能
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
