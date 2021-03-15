package com.x.gateway.zuul.apollo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Leo
 */
@Configuration
@EnableApolloConfig
public class GrayApolloConfig {

    @Bean
    public GrayConfigChangeListener javaConfigSample() {
        return new GrayConfigChangeListener();
    }

    @Bean
    @RefreshScope
    public GrayUserConfig grayUserConfig() {
        return new GrayUserConfig();
    }

    @Bean
    @RefreshScope
    public UrlMapConfig urlMapConfig() {
        return new UrlMapConfig();
    }
}
