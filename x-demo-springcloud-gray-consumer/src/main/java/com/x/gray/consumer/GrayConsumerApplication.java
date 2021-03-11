package com.x.gray.consumer;

import com.x.gray.annotation.EnableGrayConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Leo
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableGrayConfig
public class GrayConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GrayConsumerApplication.class, args);
    }

}
