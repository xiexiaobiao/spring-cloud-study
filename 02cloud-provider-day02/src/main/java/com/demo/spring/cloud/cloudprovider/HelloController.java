package com.demo.spring.cloud.cloudprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * @Classname HelloController
 * @Description TODO
 * @Author KOOL
 * @Date 2019-05-12 11:13
 * @Version 1.0
 **/
@RestController //这里有个注意的地方：此类要和入口类放一个包下，或者添加包扫描设置，否则访问不到该controller
public class HelloController {
    private final Logger logger = Logger.getLogger(String.valueOf(this.getClass()));

    @Autowired
    //注意这里不是 com.netflix.discovery 的 DiscoveryClient
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String index(){
        List<ServiceInstance> instances = discoveryClient.getInstances("hello-service");
        for (int i = 0; i < instances.size(); i++) {
            logger.info("/hello,host="+instances.get(i).getHost()+"service:"+instances.get(i).getServiceId());
        }
        return "Hello world!!";
    }

}
