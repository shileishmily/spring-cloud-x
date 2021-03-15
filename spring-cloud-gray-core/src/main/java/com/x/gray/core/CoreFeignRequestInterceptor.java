package com.x.gray.core;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Leo
 */
@Slf4j
public class CoreFeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        String hystrixVer = CoreHeaderInterceptor.version.get();
        log.info("set header version={} in CoreFeignRequestInterceptor", hystrixVer);

        template.header(CoreHeaderInterceptor.HEADER_VERSION, hystrixVer);
    }

}