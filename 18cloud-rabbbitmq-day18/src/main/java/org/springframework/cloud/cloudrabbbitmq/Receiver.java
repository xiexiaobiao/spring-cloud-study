package org.springframework.cloud.cloudrabbbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @Classname Receiver
 * @Description TODO
 * @Author xiexiaobiao
 * @Date 2019-05-25 15:51
 * @Version 1.0
 **/
@Component //DI
//@RabbitListener//指定监听queue
public class Receiver {

    @RabbitHandler//表示process方法是用来处理接收到的消息的
    @RabbitListener(queues = "queue2")//指定监听queue
    public void process(Message message) throws UnsupportedEncodingException {
            System.out.println("received 接收 :  " + new String(message.getBody(), "UTF-8"));

    }
}
/*学习点：@RabbitListener要放到方法上，如果放类上，会出现No method found for class [B) 的错误*/
