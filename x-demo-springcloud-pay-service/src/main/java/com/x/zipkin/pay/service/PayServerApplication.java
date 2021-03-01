package com.x.zipkin.pay.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @author Leo
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class PayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayServerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(value = "pay", method = RequestMethod.GET)
    public String pay() {
        String str1 = restTemplate().getForObject("http://x-demo-springcloud-user-service/user", String.class);
        String str2 = restTemplate().getForObject("http://x-demo-springcloud-order-service/makeOrder", String.class);
        return str1 + " " + str2;
    }
}

