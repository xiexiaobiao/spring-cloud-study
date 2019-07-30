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
    private long id;

    protected BookCommand(Setter setter,RestTemplate restTemplate,long id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id =id;
    }

    //正常调用
    @Override
    protected Book run(){
        return restTemplate.getForObject("http://TEMPLATE-PROVIDER/getBook5/{1}", Book.class,id);
    }

    //降级调用
    @Override
    protected Book getFallback() {
        Throwable executionException = getExecutionException();
        System.out.println(executionException.getMessage());
        System.out.println("来自于 getFallback()");
        return new Book("宋诗选注", 88, "钱钟书", "三联书店");
    }

    protected String getCacheKey(){
        return String.valueOf(id);
    }
}
