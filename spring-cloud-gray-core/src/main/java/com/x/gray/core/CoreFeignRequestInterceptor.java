package com.x.gray.core;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoreFeignRequestInterceptor implements RequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(CoreHttpRequestInterceptor.class);

    @Override
    public void apply(RequestTemplate template) {
        String hystrixVer = CoreHeaderInterceptor.version.get();
        logger.debug("====>fegin version:{} ", hystrixVer);

        template.header(CoreHeaderInterceptor.HEADER_VERSION, hystrixVer);
    }

}