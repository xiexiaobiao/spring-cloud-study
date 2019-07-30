package org.springframework.cloud.provider.controller;

import com.biao.study.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Classname HelloController
 * @Description TODO
 * @Author KOOL
 * @Date 2019-05-12 20:45
 * @Version 1.0
 **/
@RestController//这里有个注意的地方：此类要和入口类放一个包下，或者添加包扫描设置，否则访问不到该controller
public class HelloController {
    private Logger logger =  Logger.getLogger(String.valueOf(this.getClass()));

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public  String index(){
        List<ServiceInstance> instances =discoveryClient.getInstances("template-provider");
        for (int i = 0; i < instances.size(); i++) {
            logger.info("provider-service/,host="+instances.get(i).getHost()+"service:"+instances.get(i).getServiceId());
        }
        return  "Hello World";
    }

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello(String name) {
        return "hello " + name;
    }

    //直接返回一个对象
    @RequestMapping(value = "/getBook1",method = RequestMethod.GET)
    public Book getBook1(){
        return new Book("三国演义", 90, "罗贯中", "花城出版社0002");
    }

    //使用post方式接收对象参数
    @RequestMapping(value = "/getBook2",method = RequestMethod.POST)
    public Book getBook2(@RequestBody Book book){
        System.out.println(book.getName());
        book.setPrice(33);
        book.setAuthor("曹雪芹");
        book.setPublisher("人民文学出版社333");
        return  book;
    }

    //day09 测试缓存使用
    @RequestMapping(value = "/getBook5/{id}",method = RequestMethod.GET)
    public Book book5(@PathVariable("id") Integer id){
        System.out.println(">>>>>>>>/getBook5/{id}");
        if (id == 1) {
            return new Book("《李自成》", 55, "姚雪垠", "人民文学出版社");
        } else if (id == 2) {
            return new Book("中国文学简史", 33, "林庚", "清华大学出版社");
        }
        return new Book("文学改良刍议", 33, "胡适", "无");
    }

    //测试Hystrix请求合并使用，这里是一个批处理多对象提供方法
    @RequestMapping("/getBook6")
    public List books6(String ids) {
        System.out.println("ids>>>>>>>>>>>>>>>>>>>>>" + ids);
        List<Book> books = new ArrayList();
        books.add(new Book("《李自成》", 55, "姚雪垠", "人民文学出版社"));
        books.add(new Book("中国文学简史", 33, "林庚", "清华大学出版社"));
        books.add(new Book("文学改良刍议", 33, "胡适", "无"));
        books.add(new Book("ids", 22, "helloworld", "haha"));
        return books;
    }

    //测试Hystrix请求合并使用，这里是一个单对象提供方法
    @RequestMapping("/getBook6/{id}")
    public Book book61(@PathVariable("id") String id){
        Book book = new Book("《李自成》2", 55, "姚雪垠2", "人民文学出版社2");
        return book;
    }

    //day12,测试feign提供的方法,/hello-interface1用于使用者（13cloud-feign-consumer-day13）绑定
    @RequestMapping(value = "/hello-interface1",method = RequestMethod.GET)
    public String hello2(){
        System.out.println(this.getClass().getName()+"hello2()");
        return "hello";
    }

    //测试feign提供的方法，传参数
    @RequestMapping(value = "/hello-interface2",method = RequestMethod.GET)
    public String hello3(@RequestParam String name){
        System.out.println(this.getClass().getName()+"hello3()");
        return "string>>>>>>>>>>"+name;
    }

    //测试feign提供的方法,传请求头参数
    @RequestMapping(value="/hello-interface3",method = RequestMethod.GET)
    public Book hello4(@RequestHeader String name,@RequestHeader String author,@RequestHeader Integer price) throws UnsupportedEncodingException {
        Book book = new Book();
        book.setName(URLDecoder.decode(name,"utf-8"));
        book.setAuthor(URLDecoder.decode(author,"utf-8"));
        book.setPrice(price);
        System.out.println(book);
        return book;
    }

    //测试feign提供的方法,传请求体参数
    @RequestMapping(value="/hello-interface4",method = RequestMethod.POST)
    public String hello5(@RequestBody Book book){
        return "book name:"+ book.getName()+" author: "+book.getAuthor();
    }
}
