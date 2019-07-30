package org.springframework.cloud.cloudfeignconsumer;

import com.biao.study.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * @Classname FeignConsumerController
 * @Description TODO
 * @Author KOOL
 * @Date 2019-05-18 14:47
 * @Version 1.0
 **/
@RestController
public class FeignConsumerController {
    //参数不同，调用不同的hello方法
    @Autowired
    private HelloService helloService;


    @RequestMapping("/hello-feign")//注意这里，如果controller中有mapping，则在service上定义的mapping失效
    public String helloFeign(){
        return helloService.hello();
    }

    @RequestMapping("/hello-feign2")
    public String helloFeign2(){
        return helloService.hello("zhang-San");
    }

    @RequestMapping("/hello-feign3")
    public String helloFeign3() throws UnsupportedEncodingException {
        return helloService.hello(URLEncoder.encode("三国演义","UTF-8"),URLEncoder.encode("罗贯中","UTF-8"),33);
    }

    @RequestMapping("/hello-feign4")
    public String helloFeign4(){
        Book book = new Book();
        book.setName("红楼梦");
        book.setPrice(44);
        book.setAuthor("曹雪芹");
        return helloService.hello(book);
    }


    /*day15 测试feign继承*//*测试feign继承*//*测试feign继承*//*测试feign继承*/
    @Autowired
    private HelloAPIService2 helloAPIService2;

    //注意这里，如果controller中有mapping，则在service上定义的mapping失效，如果是service+方法定义mapping，可形成多级mappiing
    @RequestMapping("/hello11")//经测试，这里不需要保持与原API的mapping一致
    public String hello1(){
       return helloAPIService2.hello("li-si");
    }

    //！！！注意这里，如果controller中有mapping，则在service上定义的mapping失效，
    @RequestMapping("/hello22")//经测试，这里不需要保持与原API的mapping一致
    public Book hello2() throws UnsupportedEncodingException {
        Book book = helloAPIService2.hello(URLEncoder.encode("三国演义","UTF-8"), URLEncoder.encode("罗贯中","UTF-8"), 33);
        System.out.println(book);
        return book;
    }

    @RequestMapping("/hello33")//经测试，这里不需要保持与原API的mapping一致
    public String hello3(){
        Book book = new Book();
        book.setName("红楼梦2");
        book.setPrice(44);
        book.setAuthor("曹雪芹2");
        return helloAPIService2.hello(book);
    }
}
