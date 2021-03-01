package com.x.gateway.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;


/**
 * @author Leo
 */
@Component
public class PreFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(PreFilter.class);

    /**
     * 过滤器的类型，它决定过滤器在请求的哪个生命周期中执行。这里定义为 pre，代表会在请求被路由之前执行
     *
     * @return
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 过滤器的执行顺序。当请求在一个阶段中存在多个过滤器时，需要根据该方法返回值来依次执行。
     *
     * @return
     */
    @Override
    public int filterOrder() {
        //过滤器执行顺序，数字越小，优先级越高
        return PRE_DECORATION_FILTER_ORDER - 5;
    }

    /**
     * 判断该过滤器是否需要被执行。这里直接返回了 true，因此该过滤器对所有请求都会生效。实际运用中可以利用该函数来指定过滤器的有效范围。
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        //返回 true，拦截所有 URL
        return true;
    }

    /**
     * 过滤器的具体逻辑。
     * 这里通过设置requestContext.setSendZuulResponse(false)过滤该请求，不对其进行路由，
     * 然后通过requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value()) 设置了其返回的错误码。
     * 当然也可以对返回的结果进行优化，比如，通过requestContext.setResponseBody(body) 对返回的 body 内容进行编辑等。
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        log.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());

        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            log.warn("token is empty");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            return null;
        }
        log.info("token is ok");
        return null;
    }
}
