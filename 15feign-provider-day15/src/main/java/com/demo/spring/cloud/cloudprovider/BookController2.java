package com.demo.spring.cloud.cloudprovider;

import com.biao.study.Book;
import com.biao.study2.HelloAPIService;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @Classname BookController2
 * @Description TODO
 * @Author KOOL
 * @Date 2019-05-18 19:07
 * @Version 1.0
 **/
@RestController
public class BookController2 implements HelloAPIService {

    @Override
    public String hello(@RequestParam("name") String s) {
        return "hello: "+s;
    }

    @Override
    public Book hello(@RequestHeader("name") String name,@RequestHeader("author") String author, @RequestHeader("price") Integer price)
   {
        Book book = new Book();
       try {
           book.setName(URLDecoder.decode(name,"UTF-8"));
           book.setAuthor(URLDecoder.decode(author,"UTF-8"));
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       }
        book.setPrice(price);
        System.out.println(book);
        return book;
    }

    @Override
    public String hello(Book book) {
        return "书名为：" + book.getName() + ";作者为：" + book.getAuthor();
    }
}
