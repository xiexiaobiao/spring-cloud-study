package org.springframework.cloud.ribbonconsumer;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

/**
 * @Classname BookCommand
 * @Description TODO
 * @Author KOOL
 * @Date 2019-05-15 21:18
 * @Version 1.0
 **/
public class BookCommand extends HystrixCommand<Book> {

    private RestTemplate restTemplate;

    protected BookCommand(Setter setter,RestTemplate restTemplate) {
        super(setter);
        this.restTemplate = restTemplate;
    }

    //正常调用
    @Override
    protected Book run(){
        return restTemplate.getForObject("http://TEMPLATE-PROVIDER/getBook1", Book.class);
    }

    //降级调用
    @Override
    protected Book getFallback() {
        return new Book("宋诗选注", 88, "钱钟书", "三联书店");
    }
}
