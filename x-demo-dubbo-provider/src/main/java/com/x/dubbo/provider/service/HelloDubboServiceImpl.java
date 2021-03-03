package com.x.dubbo.provider.service;

import com.x.demo.api.HelloDubboService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;

/**
 * @author Leo
 */
@Slf4j
@DubboService(version = "1.0.0", interfaceClass = HelloDubboService.class, methods = {
        @Method(name = "sayHello")
})
public class HelloDubboServiceImpl implements HelloDubboService {
    @Override
    public void sayHello() {
        log.info("Hello");
    }

    @Override
    public String sayHi(String name) {
        log.info("Hello: {}", name);

        return "Hello: " + name;
    }
}
