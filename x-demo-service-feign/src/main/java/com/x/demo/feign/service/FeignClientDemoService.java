package com.x.demo.feign.service;

import com.x.demo.feign.fallback.FeignClientDemoServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Leo
 */
@FeignClient(value = "x-demo-service", fallback = FeignClientDemoServiceFallback.class)
public interface FeignClientDemoService {

    @RequestMapping(value = "demo/service", method = RequestMethod.GET)
    String service();
}