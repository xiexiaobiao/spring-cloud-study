package org.springframework.cloud.ribbonconsumer;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Classname controller
 * @Description TODO
 * @Author KOOL
 * @Date 2019-05-12 13:04
 * @Version 1.0
 **/
@RestController
public class ConsumerController {

    //由启动处提供一个restTemplate
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/ribbon-cache",method = RequestMethod.GET)
    public Book helloController(){
        HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("commandKey");
        HystrixRequestContext.initializeContext();
        BookCommand bookCommand1 =
                new BookCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(""))
                        .andCommandKey(commandKey), restTemplate,1l);
        //同步调用
        Book book1 = bookCommand1.execute();
        BookCommand bookCommand2 =
                new BookCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(""))
                        .andCommandKey(commandKey), restTemplate,1l);
        //同步调用
        Book book2 = bookCommand2.execute();
        BookCommand bookCommand3 =
                new BookCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(""))
                        .andCommandKey(commandKey), restTemplate,1l);
        //同步调用
        Book book3 = bookCommand3.execute();
        System.out.println("book1:" + book1);
        System.out.println("book2:" + book2);
        System.out.println("book3:" + book3);
        return  book1;

    }

}
