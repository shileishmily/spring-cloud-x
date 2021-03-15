package com.x.gateway.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.x.gateway.zuul.apollo.GrayUserConfig;
import com.x.gray.core.CoreHeaderInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Leo
 */
@Slf4j
@Component
public class GrayFilter extends ZuulFilter {

    @Autowired
    private GrayUserConfig grayUserConfig;

    private static final String HEADER_TOKEN = "token";
    private static final Logger logger = LoggerFactory.getLogger(GrayFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1000;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String token = ctx.getRequest().getHeader(HEADER_TOKEN);

        String userId = token;
        log.info("GrayFilter 客户端请求头userId: {}", userId);

        List<String> userIdList = grayUserConfig.getUserIdList();
        log.info("GrayFilter Zuul网关配置的灰度用户列表: {}", userIdList);

        String version = userIdList.contains(userId) ? grayUserConfig.getVersion() : null;
        logger.info("GrayFilter userId:{}, version:{}", userId, version);

        // zuul本身调用微服务
        CoreHeaderInterceptor.initHystrixRequestContext(version);
        // 传递给后续微服务
        ctx.addZuulRequestHeader(CoreHeaderInterceptor.HEADER_VERSION, version);

        return null;
    }
}
