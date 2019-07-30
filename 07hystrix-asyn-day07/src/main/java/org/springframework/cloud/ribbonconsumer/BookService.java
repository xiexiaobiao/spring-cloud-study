package org.springframework.cloud.ribbonconsumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * @Classname BookService
 * @Description TODO
 * @Author KOOL
 * @Date 2019-05-16 20:07
 * @Version 1.0
 **/
@Service
public class BookService {

    @Autowired
    RestTemplate restTemplate;

    //降级指定忽略哪些异常
    @HystrixCommand(fallbackMethod = "error1",ignoreExceptions = ArithmeticException.class)
    public Future<Book> test4(){
        int i = 1 / 0;//触发错误导致降级发生，或者停掉TEMPLATE-PROVIDER服务
        return new AsyncResult<Book>() {
            @Override
            public Book invoke() {
                return restTemplate.getForObject("http://TEMPLATE-PROVIDER/getBook1", Book.class);
            }
        };
    }

    //第一次降级方法
    @HystrixCommand(fallbackMethod = "error2")
    //只需要在服务降级方法中添加一个Throwable类型的参数就能够获取到抛出的异常的类型
    public Book error1(Throwable throwable){
        System.out.println(throwable.getMessage());
        return new Book("百年孤独", 33, "马尔克斯", "人民文学出版社");
    }

    //第二次降级处理方法
    public Book error2(){
        return new Book();
    }

}
