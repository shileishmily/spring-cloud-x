package com.x.gateway.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.x.gray.core.CoreHeaderInterceptor;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * @author Leo
 */
@Component
public class GrayPostZuulFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
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
        CoreHeaderInterceptor.shutdownHystrixRequestContext();
        return null;
    }
}
