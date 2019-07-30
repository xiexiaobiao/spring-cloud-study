package org.springframework.controller.ribbonconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Classname controller
 * @Description TODO
 * @Author KOOL
 * @Date 2019-05-12 13:04
 * @Version 1.0
 **/
@RestController
public class ConsumerController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/ribbon-consumer",method = RequestMethod.GET)
    public String helloController(){
        return restTemplate
                .getForEntity("http://hello-service/hello",String.class).getBody();
    }

}
