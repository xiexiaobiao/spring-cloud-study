package com.biao.study2;

import com.biao.study.Book;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/hs2")//注意这里，就是多级的mapper
public interface HelloAPIService {
    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);

    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    String hello(@RequestHeader("name") String name,@RequestHeader("author") String author,@RequestHeader Integer price);

    @RequestMapping(value = "/hello3",method = RequestMethod.POST)
    String hello(@RequestBody Book book);
}
