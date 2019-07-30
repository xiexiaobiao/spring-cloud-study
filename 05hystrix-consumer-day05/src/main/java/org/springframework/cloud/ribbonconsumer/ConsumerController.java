package org.springframework.cloud.ribbonconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    HelloService helloService;

    @RequestMapping(value = "/ribbon-consumer",method = RequestMethod.GET)
    public String helloController(){
        return helloService.hello();
    }

}
