package org.springframework.cloud.ribbonconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /*
    //由启动处提供一个restTemplate
    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping(value = "/ribbon-override",method = RequestMethod.GET)
    public Book helloController() throws ExecutionException, InterruptedException {
        BookCommand bookCommand =
                new BookCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")), restTemplate);
        //同步调用
        Book book1 = bookCommand.execute();
        //异步调用
        Future<Book> queue = bookCommand.queue();
        Book book = queue.get();
        return  book;
    }*/

    @Autowired
    BookService bookService;

    @RequestMapping("/asyn")
    public Book test3() throws ExecutionException, InterruptedException {
        Future<Book> bookFuture = bookService.test4();
        return bookFuture.get();
    }
}
