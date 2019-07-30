package org.springframework.cloud.cloudfeignconsumer;


import com.biao.study.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


/**
 * @Classname HelloService
 * @Description TODO
 * @Author KOOL
 * @Date 2019-05-18 14:37
 * @Version 1.0
 **/
@Service
@FeignClient(serviceId = "template-provider")//将此接口绑定到一个注册的服务（04cloud-resttemplate-day04）上
//参数不同，重载hello方法，
public interface HelloService {
    @RequestMapping("/hello-interface1")//绑定服务提供者提供的接口，即mapper路径（）
    String hello(); //方法名不要求必须对应

    @RequestMapping(value = "/hello-interface2",method = RequestMethod.GET)//绑定服务提供者提供的接口，即mapper路径
    String hello(@RequestParam("name") String name); //方法名不要求必须对应

    @RequestMapping(value = "/hello-interface3",method = RequestMethod.GET)//绑定服务提供者提供的接口，即mapper路径
    /*在SpringMVC中，@RequestParam和@RequestHeader注解，如果我们不指定value，则默认采用参数的名字作为其value，
    但是在Feign中，这个value必须明确指定，否则会报错*/
    String hello(@RequestHeader("name") String name,@RequestHeader("author") String author,@RequestHeader("price") Integer price); //方法名不要求必须对应

    @RequestMapping(value = "/hello-interface4",method = RequestMethod.POST)//绑定服务提供者提供的接口，即mapper路径
    String hello(@RequestBody Book book); //方法名不要求必须对应
}
