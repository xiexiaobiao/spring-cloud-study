package org.springframework.cloud.cloudrabbbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @Classname Sender
 * @Description TODO
 * @Author xiexiaobiao
 * @Date 2019-05-25 15:39
 * @Version 1.0
 **/
@Component
public class Sender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() throws UnsupportedEncodingException {
        String msg = "有过期时间属性的消息"+java.time.LocalTime.now().toString();
        //指定消息的过期时间TTL
        Message message = MessageBuilder.withBody(msg.getBytes("UTF-8")).build();
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        message.getMessageProperties().setExpiration("10000");

        System.out.println("--发送: "+ new String(message.getBody(),"UTF-8"));
        //hello1是queue的名称.这里建立了两个queue，发送消息至queue1，取消息在queue2，但由于message设置了TTL，
        //  故运行后，并不能马上取得消息，即实现了 延时任务，特别适用于 30分钟取消订单的业务
        this.amqpTemplate.convertAndSend("queue1",message);
    }

}
