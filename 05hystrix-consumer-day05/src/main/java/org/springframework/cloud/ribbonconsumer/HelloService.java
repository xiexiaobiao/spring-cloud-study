package org.springframework.cloud.ribbonconsumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Classname HelloService
 * @Description TODO
 * @Author KOOL
 * @Date 2019-05-14 19:18
 * @Version 1.0
 **/
@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "error")//指定请求失败时回调的方法
    public String hello(){
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class);
        return  responseEntity.getBody();
    }

    //定义error方法，供调用
    public String error(){
        return  "error，please retry later!";
    }
}
