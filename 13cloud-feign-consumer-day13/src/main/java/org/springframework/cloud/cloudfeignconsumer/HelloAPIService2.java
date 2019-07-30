package org.springframework.cloud.cloudfeignconsumer;

import com.biao.study2.HelloAPIService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@FeignClient(serviceId = "feign-provider2")//将此接口绑定到一个注册的服务上
@RequestMapping("/hs2")//注意这里可以加mapping，形成多级的mapper
public interface HelloAPIService2 extends HelloAPIService {
}
