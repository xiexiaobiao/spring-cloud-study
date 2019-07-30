package org.springframework.cloud.cloudconfigclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname HelloController
 * @Description TODO
 * @Author xiexiaobiao
 * @Date 2019-05-21 20:49
 * @Version 1.0
 **/

@RefreshScope//开启 SpringCloudConfig 客户端的 refresh 刷新范围，来获取服务端的最新配置
@RestController
public class HelloController {
    @Value("${sang}")//自动赋值
    String sang;
    @Autowired
    Environment environment;

    @RequestMapping("/sang")
    public String sang() {
        return this.sang;
    }

    @RequestMapping("/sang2")
    public String sang2(){
        System.out.println(environment.toString());
        return environment.getProperty("sang","no defined");
    }
}
