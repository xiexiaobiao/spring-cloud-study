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

    @RequestMapping(value = "/ribbon-override",method = RequestMethod.GET)
    public Book helloController() throws ExecutionException, InterruptedException {

        HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("commandKey");
        HystrixRequestContext.initializeContext();
        BookCommand bookCommand = new BookCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandKey(commandKey), restTemplate);

        /*一定注意下面的同步和异步不能同时使用，如下就会出错，只能保留一种方式*/

        /*//同步调用
        Book book1 = bookCommand.execute();*/

        //异步调用
        Future<Book> queue = bookCommand.queue();
        Book book1 = queue.get();

        return  book1;

    }

}
