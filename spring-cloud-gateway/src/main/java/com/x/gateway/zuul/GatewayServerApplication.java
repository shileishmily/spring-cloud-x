package com.x.gateway.zuul;

import com.x.gray.annotation.EnableGrayConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


/**
 * @author Leo
 */
@SpringBootApplication
@EnableZuulProxy
@EnableGrayConfig
public class GatewayServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }
}
