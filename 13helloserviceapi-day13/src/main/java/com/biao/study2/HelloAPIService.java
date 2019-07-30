package com.biao.study2;

import com.biao.study.Book;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname HelloAPIService
 * @Description TODO
 * @Author KOOL
 * @Date 2019-05-18 20:30
 * @Version 1.0
 **/

@RequestMapping("/hs2")//注意这里，就是多级的mapper
public interface HelloAPIService {
    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);

    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    Book hello(@RequestHeader("name") String name, @RequestHeader("author") String author, @RequestHeader("price") Integer price);

    @RequestMapping(value = "/hello3",method = RequestMethod.POST)
    String hello(@RequestBody Book book);
}
