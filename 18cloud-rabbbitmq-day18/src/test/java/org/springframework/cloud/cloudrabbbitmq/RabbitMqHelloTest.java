package org.springframework.cloud.cloudrabbbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

/**
 * @Classname RabbitMqHelloTest
 * @Description TODO
 * @Author xiexiaobiao
 * @Date 2019-05-25 15:59
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CloudRabbbitmqApplication.class)
public class RabbitMqHelloTest {
    @Autowired
    private Sender sender;

    @Test
    public void sendTest() throws InterruptedException, UnsupportedEncodingException {

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            sender.send();
        }
//！！！！这里有个注意点，因为Receiver会因为@Component自动注入，所以测试这里并不需要显示调用，后台自动会执行其绑定的process方法

    }

}
