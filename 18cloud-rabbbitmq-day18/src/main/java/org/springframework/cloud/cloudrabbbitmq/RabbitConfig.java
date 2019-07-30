package org.springframework.cloud.cloudrabbbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Classname RabbitConfig
 * @Description TODO
 * @Author xiexiaobiao
 * @Date 2019-05-25 15:55
 * @Version 1.0
 **/
@Configuration
public class RabbitConfig {
    @Bean
    //注意这里的Queue是springframework.amqp.core.Queue，不是Util中的queue
    // 如果通过 rabbitmq的web端建立了queue，这里也可以不建立，
    public Queue helloQueue(){
        return new Queue("hello");
    }
}
