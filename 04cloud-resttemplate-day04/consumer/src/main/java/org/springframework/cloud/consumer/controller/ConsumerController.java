package org.springframework.cloud.consumer.controller;

import com.biao.study.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname ConsumerController
 * @Description TODO
 * @Author KOOL
 * @Date 2019-05-12 20:59
 * @Version 1.0
 **/
@RestController
public class ConsumerController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/ribbon-consumer",method = RequestMethod.GET)
    public String helloController(){
        return  restTemplate.getForEntity("http://template-provider/hello",String.class).getBody();
    }

    @RequestMapping(value = "/getHello",method = RequestMethod.GET)
    public String getHello(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://template-provider/hello",String.class);
        String res_body         = responseEntity.getBody();
        HttpStatus httpStatusCode   = responseEntity.getStatusCode();
        int statusCodeValue     = responseEntity.getStatusCodeValue();
        HttpHeaders httpHeaders = responseEntity.getHeaders();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("responseEntity.getBody()：").append(res_body).append("<hr>")
                .append("responseEntity.getStatusCode()：").append(httpStatusCode).append("<hr>")
                .append("responseEntity.getStatusCodeValue()：").append(statusCodeValue).append("<hr>")
                .append("responseEntity.getHeaders()：").append(httpHeaders).append("<hr>");
        return  stringBuffer.toString();
    }

    //下面三个例子是说明传参数，template-provider/hello需要建立对应的接收方法
    @RequestMapping(value = "/sayHello",method = RequestMethod.GET)
    public String sayHello(){
        ResponseEntity<String> responseEntity = restTemplate
                .getForEntity("http://TEMPLATE-PROVIDER/sayHello?name={1}",String.class,"zhangsan");
        return responseEntity.getBody();
    }

    //02
    @RequestMapping(value = "/sayHello2",method = RequestMethod.GET)
    public String sayHello2(){
        Map<String,String> map = new HashMap<>();
        map.put("name","li_si");
        ResponseEntity<String> responseEntity = restTemplate
                .getForEntity("http://TEMPLATE-PROVIDER/sayHello?name={name}",String.class,map);
        return responseEntity.getBody();
    }

    //03
    @RequestMapping(value = "sayHello3",method = RequestMethod.GET)
    public String sayHello3(){
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://TEMPLATE-PROVIDER/sayHello?name={name}")
                .build().expand("wang_wu").encode();
        URI uri = uriComponents.toUri();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri,String.class);
        return  responseEntity.getBody();
    }

    //测试地址：http://localhost:9000/book1
    @RequestMapping(value = "/book1",method = RequestMethod.GET)
    public Book book1(){
        return new Book("三国演义", 90, "罗贯中", "花城出版社");
    }

    //getBook1要对应TEMPLATE-PROVIDER的地址
    @RequestMapping("/book2")
    public Book book2() {
        ResponseEntity<Book> responseEntity = restTemplate.getForEntity("http://TEMPLATE-PROVIDER/getBook1", Book.class);
        return responseEntity.getBody();
    }

    //对比上面的例子，getForObject是对getForEntity函数的进一步封装，
    // 如只关注返回的消息体的内容，对其他信息都不关注，此时可以使用getForObject
    @RequestMapping("/book3")
    public Book book3() {
        Book book = restTemplate.getForObject("http://TEMPLATE-PROVIDER/getBook1", Book.class);
        return  book;
    }

    //使用post去请求其他服务
    @RequestMapping(value = "/book4",method = RequestMethod.GET)
    public Book book4(){
        Book book = new Book();
        book.setName("红楼梦");
        ResponseEntity<Book> responseEntity =
                restTemplate.postForEntity("http://TEMPLATE-PROVIDER/getBook2", book,Book.class);
        return responseEntity.getBody();
    }

    //postForLocation是提交新资源，提交成功之后，返回新资源的URI


    //put方式，更新，没有返回值
    @RequestMapping("/put")
    public void  put1(){
        Book book = new Book();
        book.setName("红楼梦");
        restTemplate.put("http://HELLO-SERVICE/getbook3/{1}",book,99);//99用来替换前面的占位符{1}
    }

    //delete方式
    @RequestMapping("/delete")
    public void delete1(){
        restTemplate.delete("http://HELLO-SERVICE/getbook4/{1}", 100);
    }
}
