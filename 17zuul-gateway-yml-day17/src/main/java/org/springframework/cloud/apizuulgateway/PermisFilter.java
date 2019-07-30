package org.springframework.cloud.apizuulgateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname PermisFilter
 * @Description TODO
 * @Author xiexiaobiao
 * @Date 2019-05-19 21:29
 * @Version 1.0
 **/
public class PermisFilter extends ZuulFilter {

    @Override
    public String filterType() {
        //其他可选值还有post、error、route和static
        return "pre";
    }

    //此filter的执行顺序
    @Override
    public int filterOrder() {
        return 0;
    }

    //是否开启此filter
    @Override
    public boolean shouldFilter() {
        return false;
    }

    //filter的具体逻辑
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String login = ((HttpServletRequest) request).getParameter("login");
        if (login == null){
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.addZuulResponseHeader("content-type","text/html;charset=utf-8");
            ctx.setResponseBody("非法访问");
        }
        return null;
    }
}