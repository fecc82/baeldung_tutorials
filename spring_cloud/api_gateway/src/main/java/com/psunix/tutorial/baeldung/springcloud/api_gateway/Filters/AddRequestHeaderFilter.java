package com.psunix.tutorial.baeldung.springcloud.api_gateway.Filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AddRequestHeaderFilter extends ZuulFilter{
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ct = RequestContext.getCurrentContext();
        ct.addZuulRequestHeader("x-location","miami");
        return null;
    }
}
