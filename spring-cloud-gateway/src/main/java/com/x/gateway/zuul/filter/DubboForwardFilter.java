package com.x.gateway.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.x.gateway.zuul.util.DubboCallbackUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.List;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ROUTE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;

/**
 * Dubbo泛化调用
 *
 * @author Leo
 */
@Slf4j
@Component
public class DubboForwardFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SEND_RESPONSE_FILTER_ORDER - 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        log.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());

        /*if ("POST".equals(request.getMethod())) {
            LinkedHashMap param = null;
            try (InputStream inputStream = request.getInputStream()) {
                String body = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
                log.info("原始请求参数：{}", body);
                param = JSONObject.parseObject(body, LinkedHashMap.class, Feature.OrderedField);

                String interfaceName = (String) param.get("interfaceName");
                String method = (String) param.get("method");
                String version = (String) param.get("version");
                String address = (String) param.get("address");
                String json = param.get("param").toString();
                List<Object> paramList = JSONObject.parseArray(json);

                if (!StringUtils.isEmpty(interfaceName) && !StringUtils.isEmpty(method)) {

                    Object responseTxt = DubboCallbackUtil.invoke(interfaceName, method, paramList, address, version);
                    requestContext.setSendZuulResponse(true);
                    requestContext.setResponseStatusCode(HttpStatus.OK.value());
                    requestContext.setResponseBody((String) responseTxt);
                }
            } catch (IOException e) {
                log.error("dubbo泛化调动异常", e);
            }
        }*/

        return null;
    }
}
