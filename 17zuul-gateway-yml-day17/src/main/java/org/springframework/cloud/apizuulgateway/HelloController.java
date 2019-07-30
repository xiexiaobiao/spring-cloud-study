package org.springframework.cloud.apizuulgateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname HelloController
 * @Description TODO
 * @Author xiexiaobiao
 * @Date 2019-05-20 22:13
 * @Version 1.0
 **/
@RestController
public class HelloController {
    @RequestMapping("/local")
    public String hello(){
        return "hello api gateway";
    }
}
