package com.x.alertmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Leo
 */
@SpringBootApplication
@EnableEurekaClient
public class AlertManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlertManagerApplication.class, args);
    }

}
