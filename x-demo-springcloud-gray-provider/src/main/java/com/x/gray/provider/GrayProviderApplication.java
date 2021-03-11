package com.x.gray.provider;

import com.x.gray.annotation.EnableGrayConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Leo
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableGrayConfig
public class GrayProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(GrayProviderApplication.class, args);
    }
}