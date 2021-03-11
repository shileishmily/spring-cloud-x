package com.x.demo.config.client;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Leo
 */
@SpringBootApplication
@EnableApolloConfig
public class ApolloConfigClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApolloConfigClientApplication.class, args);
    }

}