package com.x.demo.ribbon.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("ribbon")
@Slf4j
public class RibbonDemoController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @HystrixCommand(fallbackMethod = "ribbonFallback")
    @RequestMapping("service")
    public String service() {
        log.info("[ribbon]谁在撩我?");

        //随机访问策略
        this.loadBalancerClient.choose("x-demo-service");
        return restTemplate.getForObject("http://X-DEMO-SERVICE/demo/service", String.class);
    }

    private String ribbonFallback() {
        return "我是Ribbon，服务端大概率挂了！";
    }
}
