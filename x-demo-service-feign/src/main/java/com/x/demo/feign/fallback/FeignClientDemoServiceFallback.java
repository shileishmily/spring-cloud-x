package com.x.demo.feign.fallback;

import com.x.demo.feign.service.FeignClientDemoService;
import org.springframework.stereotype.Component;

/**
 * @author Leo
 */
@Component
public class FeignClientDemoServiceFallback implements FeignClientDemoService {
    @Override
    public String service() {
        return "我是Feign，服务端大概率挂了！";
    }
}
