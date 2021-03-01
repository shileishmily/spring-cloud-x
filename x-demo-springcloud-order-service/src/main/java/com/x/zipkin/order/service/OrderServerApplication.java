package com.x.zipkin.order.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leo
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class OrderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication.class, args);
    }

    @RequestMapping(value = "makeOrder", method = RequestMethod.GET)
    public String makeOrder() {
        return "下单成功";
    }
}

