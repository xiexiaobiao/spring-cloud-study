package org.springframework.cloud.cloudfeignconsumer;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


/*Netflix Feign整合了Ribbon和Hystrix，可以省去resttemplate的编写*/

@SpringBootApplication
@EnableFeignClients//开启feign
@EnableDiscoveryClient//开启注册发现
public class CloudFeignConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudFeignConsumerApplication.class, args);
    }

    @Bean
    Logger.Level feignLoggerLever(){
        return Logger.Level.FULL;
    }

}
